package com.kedacom.vector.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.kedacom.common.enums.StatusEnum;
import com.kedacom.common.response.ResponseMessage;
import com.kedacom.common.utils.RegexValidateUtil;
import com.kedacom.vector.dto.Feature;
import com.kedacom.vector.entity.*;
import com.kedacom.vector.module.ProjectListModule;
import com.kedacom.vector.module.ProjectMoudle;
import com.kedacom.vector.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.kedacom.common.contants.VectortileContants.*;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 14:51
 * @Description:
 */
@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {


    @Autowired
    IProjectService projectService;

    @Autowired
    IProjectResourceService projectResourceService;

    @Autowired
    IResourceService resourceService;

    @Autowired
    IHamletService hamletService;

    @Autowired
    IProvinceService provinceService;

    @Autowired
    IDivisionService divisionService;

    @Autowired
    ITextService textService;

    @Autowired
    IBuildingService buildingService;

    @Autowired
    IBackgroundPolygonService backgroundPolygonService;

    @Autowired
    IResidentialService residentialService;

    @Autowired
    ICityService cityService;

    @Autowired
    IDivisionTextService divisionTextService;

    @Autowired
    IPoiService poiService;

    @Autowired
    IRoadService roadService;

    @Value("${path.prefix}")
    private String pathPrefix;

    private static Integer PAGE_SIZE = 100000;

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private static Map<String,String> provinceMap = new HashMap<>();

    @RequestMapping("/addProject")
    public ResponseMessage addProject(@RequestBody @Valid ProjectMoudle projectMoudle){
        ResponseMessage responseMessage = null;

        //初始化provinceMap
        initProvinceMap();

        if(!RegexValidateUtil.isEmail(projectMoudle.getEmail())){
            return ResponseMessage.error("邮箱格式不正确");
        }
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Project project = projectService.queryProjectByUsernameAndProjectName(username,projectMoudle.getProjectName());

        if(project != null) {
            return ResponseMessage.error("该项目名称已存在！");
        }
        projectMoudle.setUsername(username);
        project = buildProject(projectMoudle);

        try {

            //查询数据库资源是否存在
            Resource resource = resourceService.queryByDataScopeAndCoordinateSystem(projectMoudle.getAdminId(),projectMoudle.getCoordinateSystem());
            if(null == resource){
                resource = buildResource(projectMoudle.getAdminId(),projectMoudle.getCoordinateSystem());
//                resourceService.insert(resource);
                executorService.execute(() -> {
                    //生产hamlet  geojson文件
                    produceHamletGeojson(projectMoudle);
                    //生产d  geojson文件
                    produceDivisionGeojson(projectMoudle);
                    //生产t  geojson文件
                    produceTextGeojson(projectMoudle);
                    //生产building  geojson数据
                    produceBuildingGeojson(projectMoudle);
                    //生产bp  geojson文件
                    produceBackgroundPolygonGeojson(projectMoudle);
                    //生产residential   geojson文件
                    produceResidentialGeojson(projectMoudle);
                    //生产dt   geojson文件
                    produceDivisionTextGeojson(projectMoudle);
                    //生产poi  geojson文件
                    producePoiGeojson(projectMoudle);
                    //生产r1  geojson文件
                    produceR1Geojson(projectMoudle);
                    //生产r21  geojson文件
                    produceR21Geojson(projectMoudle);
                    //生产r22  geojson文件
                    produceR22Geojson(projectMoudle);
                    //生产r31  geojson文件
                    produceR31Geojson(projectMoudle);
                    //生产r32  geojson文件
                    produceR32Geojson(projectMoudle);
                    //生产r33  geojson文件
                    produceR33Geojson(projectMoudle);
                    //生产r34  geojson文件
                    produceR34Geojson(projectMoudle);
                    log.info(provinceMap.get(projectMoudle.getAdminId()) + "省份的geojson数据生成完毕！");


                    String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
                    if(projectMoudle.getMode().equals(1)){
                        //生产R1.mbtiles
                        log.info("生产R1.mbtiles  start");
                        execute("tippecanoe -o " + path + File.separator + "R1.mbtiles " +
                                "-Z " + projectMoudle.getR1ZoomLevel().get(0) + " -z "+ projectMoudle.getR1ZoomLevel().get(1)
                                + " -pk -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R1.geojson");
                        log.info("生产R1.mbtiles  end");

                        //生产R21mbtiles
                        log.info("生产R21.mbtiles  start");
                        execute("tippecanoe -o " + path + "R21.mbtiles " +
                                "-Z " + projectMoudle.getR21ZoomLevel().get(0) + " -z " + projectMoudle.getR21ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R21.geojson");
                        log.info("生产R21.mbtiles  end");

                        //生产R22.mbtiles
                        log.info("生产R22.mbtiles  start");
                        execute("tippecanoe -o " + path + "R22.mbtiles " +
                                "-Z " + projectMoudle.getR22ZoomLevel().get(0) + " -z " + projectMoudle.getR22ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R22.geojson");
                        log.info("生产R22.mbtiles  end");

                        //生产R31.mbtiles
                        log.info("生产R31.mbtiles  start");
                        execute("tippecanoe -o " + path + "R31.mbtiles " +
                                "-Z " + projectMoudle.getR31ZoomLevel().get(0) + " -z " + projectMoudle.getR31ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R31.geojson");
                        log.info("生产R31.mbtiles  end");

                        //生产R32.mbtiles
                        log.info("生产R32.mbtiles  start");
                        execute("tippecanoe -o " + path + "R32.mbtiles " +
                                "-Z " + projectMoudle.getR32ZoomLevel().get(0) + " -z " + projectMoudle.getR32ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R32.geojson");
                        log.info("生产R32.mbtiles  end");

                        //生产R33.mbtiles
                        log.info("生产R33.mbtiles  start");
                        execute("tippecanoe -o " + path + "R33.mbtiles " +
                                "-Z " + projectMoudle.getR33ZoomLevel().get(0) + " -z " + projectMoudle.getR33ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R33.geojson");
                        log.info("生产R33.mbtiles  end");

                        //生产R34.mbtiles
                        log.info("生产R34.mbtiles  start");
                        execute("tippecanoe -o " + path + "R34.mbtiles " +
                                "-Z " + projectMoudle.getR34ZoomLevel().get(0) + " -z " + projectMoudle.getR34ZoomLevel().get(1)
                                + " -pk -pf -S 10 -y Kind -y PathName -y Width -y Direction -y Kind_Level " +
                                path + "R34.geojson");
                        log.info("生产R34.mbtiles  end");

                        //生产POI.mbtiles
                        log.info("生产POI.mbtiles  start");
                        execute("tippecanoe -o " + path + "POI.mbtiles " +
                                "-Z " + projectMoudle.getPoiZoomLevel().get(0) + " -z " + projectMoudle.getPoiZoomLevel().get(1)
                                + " -y Kind -y Display_x -y Display_y -y Name -y AdminCode " +
                                path + "POI.geojson");
                        log.info("生产POI.mbtiles  end");

                        //生产Buildings.mbtiles
                        log.info("生产Buildings.mbtiles  start");
                        execute("tippecanoe -o " + path + "Buildings.mbtiles " +
                                "-Z " + projectMoudle.getBuildingZoomLevel().get(0) + " -z " + projectMoudle.getBuildingZoomLevel().get(1) + " " +
                                path + "Building.geojson");
                        log.info("生产Buildings.mbtiles  end");

                        //生产Residential.mbtiles
                        log.info("生产Residential.mbtiles  start");
                        execute("tippecanoe -o " + path + "Residential.mbtiles " +
                                "-Z " + projectMoudle.getResidentialZoomLevel().get(0) + " -z " + projectMoudle.getResidentialZoomLevel().get(1) + " " +
                                path + "Residential.geojson");
                        log.info("生产Residential.mbtiles  end");

                        //生产Hamlet.mbtiles
                        log.info("生产Hamlet.mbtiles  start");
                        execute("tippecanoe -o " + path + "Hamlet.mbtiles " +
                                "-Z " + projectMoudle.getHamletZoomLevel().get(0) + " -z " + projectMoudle.getHamletZoomLevel().get(1) + " " +
                                path + "Hamlet.geojson");
                        log.info("生产Hamlet.mbtiles  end");

                        //生产T.mbtiles
                        log.info("生产T.mbtiles  start");
                        execute("tippecanoe -o " + path + "T.mbtiles " +
                                "-Z " + projectMoudle.getTZoomLevel().get(0) + " -z " + projectMoudle.getTZoomLevel().get(1)
                                + " -pk -y Kind -y Name -y AdminCode -y ID -y Class " +
                                path + "T.geojson");
                        log.info("生产T.mbtiles  end");

                        //生产DT.mbtiles
                        log.info("生产DT.mbtiles  start");
                        execute("tippecanoe -o " + path + "DT.mbtiles " +
                                "-Z " + projectMoudle.getDtZoomLevel().get(0) + " -z " + projectMoudle.getDtZoomLevel().get(1) + " " +
                                path + "DT.geojson");
                        log.info("生产DT.mbtiles  end");

                        //生产BP.mbtiles
                        log.info("生产BP.mbtiles  start");
                        execute("tippecanoe -o " + path + "BP.mbtiles " +
                                "-Z " + projectMoudle.getBpZoomLevel().get(0) + " -z " + projectMoudle.getBpZoomLevel().get(1) + " -pk -pf -S 10 " +
                                path + "BP.geojson");
                        log.info("生产BP.mbtiles  end");

                        //生产D.mbtiles
                        log.info("生产D.mbtiles  start");
                        execute("tippecanoe -o " + path + "D.mbtiles " +
                                "-Z " + projectMoudle.getDZoomLevel().get(0) + " -z " + projectMoudle.getDZoomLevel().get(1) + " -pk -pf -S 10 " +
                                path + "D.geojson");
                        log.info("生产D.mbtiles  end");
                        log.info("mbtiles生产完毕！");
                    }
                    //开始压缩数据
//                    log.info("压缩数据  start");
//                    execute("zip -r -7 -q -o " + pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + ".zip "
//                            + File.separator + pathPrefix + provinceMap.get(projectMoudle.getAdminId()));
//                    log.info("压缩数据  end");

                    //修改资源的生产状态
//                    ModifyStatus(projectMoudle);
                });
            } else {
                //将项目生产状态修改成完成
//                project.setStatus(StatusEnum.FINISHED.getValue());
            }
//            projectService.insert(project);
//            ProjectResource projectResource = buildProjectResouce(project,resource);
//            projectResourceService.insert(projectResource);
            responseMessage = ResponseMessage.ok("数据打包下载提交成功");
        } catch (Exception e) {
            log.error("数据打包提交失败！",e);
            responseMessage = ResponseMessage.error("数据打包提交失败!");
        }

        return responseMessage;
    }

    @RequestMapping("/projectList")
    public ResponseMessage projectList(@RequestBody ProjectListModule projectListModule){
        ResponseMessage responseMessage = null;
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("projectName",projectListModule.getProjectName());
        paramMap.put("startTime",projectListModule.getStartTime());
        paramMap.put("endTime",projectListModule.getEndTime());
        paramMap.put("dataScope",projectListModule.getAdminId());

        if(projectListModule.getPageNo() == null || projectListModule.getPageNo() == 0){
            projectListModule.setPageNo(1);
        }

        if(projectListModule.getPageSize() == null || projectListModule.getPageSize() == 0){
            projectListModule.setPageSize(10);
        }

        try {
            Page<Project> page = new Page<>(projectListModule.getPageNo(),projectListModule.getPageSize());
            projectService.queryProjectList(page,paramMap);
            responseMessage = ResponseMessage.ok(page);
        } catch (Exception e) {
            log.error("获取数据列表失败！",e);
            responseMessage = ResponseMessage.error("获取数据列表失败");
        }
        return responseMessage;
    }

    @RequestMapping("/download")
    public void download(@RequestParam String adminId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //初始化provinceMap
        initProvinceMap();
        File file = new File(pathPrefix + provinceMap.get(adminId) + ".zip");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        try (
                InputStream is = new FileInputStream(file);
                OutputStream os = response.getOutputStream()
        ) {
            int read = 0;
            byte[] bytes = new byte[2048];
            Long start = System.currentTimeMillis();
            while ((read = is.read(bytes)) != -1)
                os.write(bytes, 0, read);
            Long end = System.currentTimeMillis();
            log.info("下载时间为" + (end - start)/1000.0 + "s");
        }
    }

    private void initProvinceMap() {
        if(provinceMap.isEmpty()){
            List<Province> provinceList = null;
            try {
                provinceList = provinceService.selectList(Condition.create());
            } catch (Exception e) {
                log.error("查询省份信息失败！",e);
            }
            provinceList.forEach(province -> provinceMap.put(province.getAdminId(),province.getPinyin()));
        }
    }

    /**
     * 修改资源的生产状态和项目中绑定的资源状态
     * @param projectMoudle
     */
    @Transactional
    void ModifyStatus(ProjectMoudle projectMoudle) {
        Resource resource = resourceService.queryByDataScopeAndCoordinateSystem(projectMoudle.getAdminId(),projectMoudle.getCoordinateSystem());
        resource.setStatus(StatusEnum.FINISHED.getValue());
        resourceService.updateById(resource);
        projectService.updateStatus(resource.getId(),StatusEnum.FINISHED.getValue());
    }

    private Project buildProject(ProjectMoudle projectMoudle) {
        Project project = new Project();
        project.setUsername(projectMoudle.getUsername());
        project.setProjectId(UUID.randomUUID().toString());
        project.setProjectName(projectMoudle.getProjectName());
        project.setEmail(projectMoudle.getEmail());
        project.setDataScope(projectMoudle.getAdminId());
        project.setCoordinateSystem(projectMoudle.getCoordinateSystem());
        project.setLoadStyle(projectMoudle.getLoadStyle());
        project.setStatus(StatusEnum.PROCESSING.getValue());
        project.setCreateTime(new Date());
        return project;
    }

    private Resource buildResource(String adminId, String coordinateSystem) {
        Resource resource = new Resource();
        resource.setDataScope(adminId);
        resource.setCoordinateSystem(coordinateSystem);
        resource.setStatus(StatusEnum.PROCESSING.getValue());
        return resource;
    }

    private ProjectResource buildProjectResouce(Project project, Resource resource) {
        ProjectResource projectResource = new ProjectResource();
        projectResource.setProjectId(project.getProjectId());
        projectResource.setResourceId(resource.getId());
        return projectResource;
    }

    /**
     *
     * @param fileName
     * @param features
     * @param lastPage
     * @param path
     * @return 是否创建成功，成功则返回true
     */
    public boolean createFile(String fileName, List<Feature> features, boolean lastPage, String path){
        Boolean bool = false;
        String filenameTemp = path+fileName+".geojson";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        List<String> filecontent = new ArrayList<>();
        try {
            //如果文件夹不存在，则创建新的文件夹
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
                filecontent.add("{\"type\": \"FeatureCollection\",");
                filecontent.add("\"features\":[");
            }
            for (int i = 0; i < features.size(); i++) {
                filecontent.add(JSON.toJSONString(features.get(i)));
                if(!lastPage || i < features.size() - 1){
                    filecontent.add(",");
                }
            }
            if(lastPage){
                filecontent.add("]");
                filecontent.add("}");
            }
            writeFileContent(filenameTemp, filecontent);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bool;
    }

    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param lines  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,List<String> lines) throws IOException {
        Boolean bool = false;

        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        File file = new File(filepath);//文件路径(包括文件名称)
        for (String line :
                lines) {
            String filein = line+"\r\n";//新写入的行，换行
            try {

                StringBuffer buffer = new StringBuffer();
                buffer.append(filein);

                fos = new FileOutputStream(file,true);
                pw = new PrintWriter(fos);
                pw.write(buffer.toString());
                pw.flush();

            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }finally {
                //不要忘记关闭
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
            }
        }
        bool = true;
        return bool;
    }

    private void setFeatures(Object object, List<Feature> features,int i) {
        List<BaseEntity> baseEntityList = (List<BaseEntity>) object;
        for (BaseEntity baseEntity :
                baseEntityList) {
            Feature<BaseEntity> feature= new Feature<>();
            feature.setId(i++);
            feature.setProperties(baseEntity);
            feature.setGeometry(baseEntity.getGeom());
            features.add(feature);
        }
    }

    /**
     * 生产hamlet  geojson文件
     * @param projectMoudle
     */
    private void produceHamletGeojson(@RequestBody @Valid ProjectMoudle projectMoudle) {
        //查询hamlet数据
        List<Hamlet> hamletList = hamletService.queryHamletsByTableName(provinceMap.get(projectMoudle.getAdminId()) + HAMLET_SUFFIX);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(hamletList, features,i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("Hamlet",features,true, path);
    }

    /**
     * 生产d  geojson文件
     * @param projectMoudle
     */
    private void produceDivisionGeojson(ProjectMoudle projectMoudle) {
        //查询division数据
        List<Division> divisionList = divisionService.queryDivisionList(provinceMap.get(projectMoudle.getAdminId()) + DIVISION_SUFFIX);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(divisionList, features,i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("D",features,true, path);
    }

    /**
     * 生产t  geojson文件
     * @param projectMoudle
     */
    private void produceTextGeojson(ProjectMoudle projectMoudle) {
        //查询text数据
        List<Text> textList = textService.queryTextList(provinceMap.get(projectMoudle.getAdminId()) + TEXT_SUFFIX);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(textList, features,i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("T",features,true, path);
    }

    /**
     * 生产building  geojson文件
     * @param projectMoudle
     */
    private void produceBuildingGeojson(ProjectMoudle projectMoudle) {
        List<Building> buildingList = buildingService.queryBuildingList(projectMoudle.getAdminId().substring(0,2));
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(buildingList, features, i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("Building",features,true, path);
    }

    /**
     * 生产bp geojson文件
     * @param projectMoudle
     */
    private void produceBackgroundPolygonGeojson(ProjectMoudle projectMoudle) {
        List<BackgroundPolygon> backgroundPolygonList =
                backgroundPolygonService.queryBackgroundPolygonList(provinceMap.get(projectMoudle.getAdminId()) + BACKGROUND_POLYGON_SUFFIX);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(backgroundPolygonList, features, i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("BP",features,true, path);
    }

    /**
     * 生产residential   geojson文件
     * @param projectMoudle
     */
    private void produceResidentialGeojson(ProjectMoudle projectMoudle) {
        List<String> cityNames = cityService.queryCityNamesByPrefixAdminCode(projectMoudle.getAdminId().substring(0,2));
        List<Residential> residentialList = residentialService.queryResidentialList(cityNames);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(residentialList, features, i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("Residential",features,true, path);
    }

    /**
     * 生产dt  geojson文件
     * @param projectMoudle
     */
    private void produceDivisionTextGeojson(ProjectMoudle projectMoudle) {
        String geojson = divisionService.unionDivision(provinceMap.get(projectMoudle.getAdminId()) + DIVISION_SUFFIX);
        List<DivisionText> divisionTextList = divisionTextService.queryDivisionTextList(geojson);
        List<Feature> features = new ArrayList<>();
        int i = 0;
        setFeatures(divisionTextList, features, i);
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        createFile("DT",features,true, path);
    }

    /**
     * 生产poi  geojson文件
     * @param projectMoudle
     */
    private void producePoiGeojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = poiService.countPoi(provinceMap.get(projectMoudle.getAdminId()) + POI_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Poi> poiList = poiService.queryPoiList(provinceMap.get(projectMoudle.getAdminId()) + POI_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(poiList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("POI",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    private Integer getPages(Integer countPoi, Integer pageSize) {
        return countPoi / pageSize + (countPoi % pageSize == 0 ? 0 : 1);
    }


    private void produceR34Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR34(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR34List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R34",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    private void produceR33Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR33(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR33List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R33",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    private void produceR32Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR32(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR32List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R32",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    /**
     * 生产r3   geojson文件
     * @param projectMoudle
     */
    private void produceR31Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR31(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR31List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R31",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    /**
     * 生产r22   geojson文件
     * @param projectMoudle
     */
    private void produceR22Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR22(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR22List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R22",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    /**
     * 生产r21   geojson文件
     * @param projectMoudle
     */
    private void produceR21Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR21(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR21List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R21",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    /**
     * 生产r1   geojson文件
     * @param projectMoudle
     */
    private void produceR1Geojson(ProjectMoudle projectMoudle) {
        Long start = System.currentTimeMillis();
        Integer countPoi = roadService.countR1(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX);
        Long end = System.currentTimeMillis();
        log.info("查询总数耗时：" + (end - start)/1000.0 + "s");
        Integer pages = getPages(countPoi,PAGE_SIZE);
        Integer pageNo = 0;
        boolean lastPage = false; //是否最后一页
        String path = pathPrefix + provinceMap.get(projectMoudle.getAdminId()) + File.separator;
        int i = 0;
        while (pageNo < pages){
            if(pageNo == pages - 1){
                lastPage = true;
            }
            start = System.currentTimeMillis();
            List<Road> roadList = roadService.queryR1List(provinceMap.get(projectMoudle.getAdminId()) + ROAD_SUFFIX,PAGE_SIZE,pageNo);
            end = System.currentTimeMillis();
            log.info("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
            List<Feature> features = new ArrayList<>();

            setFeatures(roadList,features,i);
            i = i + PAGE_SIZE;
            start = System.currentTimeMillis();
            createFile("R1",features,lastPage,path);
            end = System.currentTimeMillis();
            log.info("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
            pageNo++;
        }
    }

    private String execute(String command) {
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            System.out.println("开始转换");
            pro = runTime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("line: " + line);
                returnString = returnString + line + "\n";
            }
            System.out.println("返回值:" + returnString);
            input.close();
//            output.close();
            pro.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnString;
    }
}