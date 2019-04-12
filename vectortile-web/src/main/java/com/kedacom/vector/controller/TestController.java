//package com.kedacom.vector.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.mapper.Condition;
//import com.kedacom.vector.dto.Feature;
//import com.kedacom.vector.entity.*;
//import com.kedacom.vector.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.*;
//import java.util.*;
//
///**
// * @Auther: YinPeng
// * @Date: 2019/2/20 0020 14:27
// * @Description:
// */
//@RestController
//public class TestController {
//
//    @Autowired
//    IHamletService hamletService;
//
//    @Autowired
//    IProvinceService provinceService;
//
//    @Autowired
//    IPoiService poiService;
//
//    @Autowired
//    IResidentialService residentialService;
//
//    @Autowired
//    ICityService cityService;
//
//    @Autowired
//    ITextService textService;
//
//    @Autowired
//    IBuildingService buildingService;
//
//    @Autowired
//    IBackgroundPolygonService backgroundPolygonService;
//
//    @Autowired
//    IDivisionService divisionService;
//
//    @Autowired
//    IDivisionTextService divisionTextService;
//
//
//    public static Map<String,String> provinceMap = new HashMap<>();
//
//
//    //生成文件路径
//    private static String path = "/root/vectortile";
//
//    //文件路径+名称
//    private static String filenameTemp;
//
//    private Integer i = 0;
//
//    @RequestMapping("/query")
//    public List<Hamlet> queryHamletByGid(){
//        return hamletService.queryHamletListByTableNameAndLikeAdminCode("jiangsu_hamlet","3205");
//    }
//
//
//    @RequestMapping("/countPoi")
//    public Integer countPoi(){
//        return poiService.countPoi("jiangsu_poi");
//    }
////    @RequestMapping("/insert")
////    public String insertHamletList(){
////        hamletService.insertHamletList();
////        return "新增成功！";
////    }
//
//    @RequestMapping("/queryHamlet")
//    public String queryHamlet(){
//        List<Hamlet> hamletList = hamletService.queryHamletsByTableName("jiangsu_hamlet");
//        List<Feature> features = new ArrayList<>();
//        i = 0;
//        setFeatures(hamletList, features);
//        createFile("hamlet",features,true);
//        return "查询成功！";
//    }
//
//    @RequestMapping("/queryPoi")
//    public String queryPoi(){
//        Integer pageSize = 10000;
//        Long start = System.currentTimeMillis();
//        Integer countPoi = poiService.countPoi("jiangsu_poi");
//        Long end = System.currentTimeMillis();
//        System.out.println("查询总数耗时：" + (end - start)/1000.0 + "s");
//        Integer pages = getPages(countPoi,pageSize);
//        Integer pageNo = 0;
//        boolean lastPage = false; //是否最后一页
//        while (pageNo < pages){
//            if(pageNo == pages - 1){
//                lastPage = true;
//            }
//            start = System.currentTimeMillis();
//            List<Poi> poiList = poiService.queryPoiList("jiangsu_poi",pageSize,pageNo);
//            end = System.currentTimeMillis();
//            System.out.println("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
//            List<Feature> features = new ArrayList<>();
//            int i = 0;
//            for (Poi poi :
//                    poiList) {
//                Feature<Poi> feature= new Feature<>();
//                feature.setId(i++);
//                feature.setProperties(poi);
//                feature.setGeometry(poi.getGeom());
//                features.add(feature);
//            }
//            start = System.currentTimeMillis();
//            createFile("poi",features,lastPage);
//            end = System.currentTimeMillis();
//            System.out.println("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
//            pageNo++;
//        }
//
//        return "查询成功！";
//    }
//
//    private Integer getPages(Integer countPoi, Integer pageSize) {
//        return countPoi / pageSize + (countPoi % pageSize == 0 ? 0 : 1);
//    }
//
//    @RequestMapping("/queryProvince")
//    public List<Province> queryProvince(){
//        List<Province> provinces = provinceService.selectList(Condition.create());
//        return provinces;
//    }
//
//    @RequestMapping("/queryText")
//    public List<Text> queryText(){
//        List<Text> texts = textService.queryTextList("jiangsu_t");
//        return texts;
//    }
//
//    @RequestMapping("/queryResidential")
//    public List<Residential> queryResidential(){
//        List<Province> provinceList = provinceService.selectList(Condition.create());
//        provinceList.forEach(province -> {
//            provinceMap.put(province.getPinyin(),province.getAdminId().substring(0,2));
//        });
//        List<String> cityNames = cityService.queryCityNamesByAdminCode(provinceMap.get("jiangsu") + "0000");
//        System.out.println(cityNames);
//        return residentialService.queryResidentialList(cityNames);
//    }
//
//    @RequestMapping("/sortData")
//    public String sortData(){
//        List<Province> provinceList = provinceService.selectList(Condition.create());
//        provinceList.forEach(province -> {
//            provinceMap.put(province.getPinyin(),province.getAdminId().substring(0,2));
//        });
//
//        Province province = provinceService.queryProvinceByPinyin("jiangsu");
//        List<String> provinceNames = Arrays.asList(province.getRelationNames().split(","));
//
//        Long start = System.currentTimeMillis();
////        provinceNames.forEach(provinceName -> {
////
////            hamletService.insertHamletList(provinceName + "_hamlet","jiangsu_hamlet","32%");
////            hamletService.deleteHamletList(provinceName + "_hamlet","32%");
////            hamletService.insertHamletList("jiangsu_hamlet",provinceName + "_hamlet",provinceMap.get(provinceName) + "%");
////            hamletService.deleteHamletList("jiangsu_hamlet",provinceMap.get(provinceName) + "%");
////        });
////        List<Hamlet> hamletList = hamletService.queryHamletsByTableName("jiangsu_hamlet");
//        List<Feature> features = new ArrayList<>();
//        i = 0;
////        setFeatures(hamletList, features, i);
////        createFile("hamlet",features,true);
//        Long end = System.currentTimeMillis();
///////////////////////////////////////////////////////////////////////////////////////////////
////        provinceNames.forEach(provinceName -> {
////            poiService.insertPoiList(provinceName + "_poi","jiangsu_poi","32%");
////            poiService.deletePoiList(provinceName + "_poi", "32%");
////            poiService.insertPoiList("jiangsu_poi", provinceName + "_poi", provinceMap.get(provinceName) + "%");
////            poiService.deletePoiList("jiangsu_poi", provinceMap.get(provinceName) + "%");
////        });
//
//        Integer pageSize = 10000;
////        Integer countPoi = poiService.countPoi("jiangsu_poi");
////        Integer pages = getPages(countPoi,pageSize);
//        Integer pageNo = 0;
//        boolean lastPage = false; //是否最后一页
//        i = 0;
////        while (pageNo < pages){
////            if(pageNo == pages - 1){
////                lastPage = true;
////            }
////            start = System.currentTimeMillis();
////            List<Poi> poiList = poiService.queryPoiList("jiangsu_poi",pageSize,pageNo);
////            end = System.currentTimeMillis();
////            System.out.println("查询第" + (pageNo + 1) + "页，耗时：" + (end - start) / 1000.0 + "s");
////            features = new ArrayList<>();
////            setFeatures(poiList, features);
////            start = System.currentTimeMillis();
////            createFile("poi",features,lastPage);
////            end = System.currentTimeMillis();
////            System.out.println("写第" + (pageNo + 1) + "页，耗时：" + (end -start)/1000.0 + "s");
////            pageNo++;
////        }
//////////////////////////////////////////////////////////////////////////////////////////////////////
//
////        List<String> cityNames = cityService.queryCityNamesByAdminCode(provinceMap.get("jiangsu") + "0000");
////        List<Residential> residentialList = residentialService.queryResidentialList(cityNames);
////        features = new ArrayList<>();
////        setFeatures(residentialList,features);
////        start = System.currentTimeMillis();
////        createFile("residential",features,true);
////        end = System.currentTimeMillis();
//
//        ///////////////////////////////////////////////////
////        provinceNames.forEach(provinceName -> {
////            textService.insertTextList(provinceName + "_t","jiangsu_t","32%");
////            textService.deleteTextList(provinceName + "_t","32%");
////            textService.insertTextList("jiangsu_t",provinceName + "_t",provinceMap.get(provinceName) + "%");
////            textService.deleteTextList("jiangsu_t",provinceMap.get(provinceName) + "%");
////        });
////
////        List<Text> textList = textService.queryTextList("jiangsu_t");
////        features = new ArrayList<>();
////        setFeatures(textList,features);
////        start = System.currentTimeMillis();
////        createFile("text",features,true);
////        end = System.currentTimeMillis();
//        //////////////////////////////////////////////////////
//
////        List<Building> buildingList = buildingService.queryBuildingList("building_region","32",pageSize,pageNo);
////        features = new ArrayList<>();
////        setFeatures(buildingList,features);
////        start = System.currentTimeMillis();
////        createFile("building",features,true);
////        end = System.currentTimeMillis();
////        System.out.println("总耗时：" + (end - start) / 1000.0 + "s");
//        return "数据整理成功！";
//    }
//
//    private void setFeatures(Object object, List<Feature> features) {
//        List<BaseEntity> baseEntityList = (List<BaseEntity>) object;
//        for (BaseEntity baseEntity :
//                baseEntityList) {
//            Feature<BaseEntity> feature= new Feature<>();
//            feature.setId(i++);
//            feature.setProperties(baseEntity);
//            feature.setGeometry(baseEntity.getGeom());
//            features.add(feature);
//        }
//    }
//
//
//    /**
//     * 创建文件
//     * @param fileName  文件名称
//     * @param features
//     * @return  是否创建成功，成功则返回true
//     */
//    public static boolean createFile(String fileName,List<Feature> features,boolean lastPage){
//        Boolean bool = false;
//        filenameTemp = path+fileName+".json";//文件路径+名称+文件类型
//        File file = new File(filenameTemp);
//        List<String> filecontent = new ArrayList<>();
//        try {
//            //如果文件不存在，则创建新的文件
//            if(!file.exists()){
//                file.createNewFile();
//                bool = true;
//                System.out.println("success create file,the file is "+filenameTemp);
//                //创建文件成功后，写入内容到文件里
//                filecontent.add("{\"type\": \"FeatureCollection\",");
//                filecontent.add("\"features\":[");
//            }
//            for (int i = 0; i < features.size(); i++) {
//                filecontent.add(JSON.toJSONString(features.get(i)));
//                if(!lastPage || i < features.size() - 1){
//                    filecontent.add(",");
//                }
//            }
//            if(lastPage){
//                filecontent.add("]");
//                filecontent.add("}");
//            }
//            writeFileContent(filenameTemp, filecontent);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return bool;
//    }
//
//    /**
//     * 向文件中写入内容
//     * @param filepath 文件路径与名称
//     * @param lines  写入的内容
//     * @return
//     * @throws IOException
//     */
//    public static boolean writeFileContent(String filepath,List<String> lines) throws IOException{
//        Boolean bool = false;
//
//        BufferedReader br = null;
//        FileOutputStream fos  = null;
//        PrintWriter pw = null;
//        File file = new File(filepath);//文件路径(包括文件名称)
//        for (String line :
//                lines) {
//            String filein = line+"\r\n";//新写入的行，换行
//            try {
//
//                StringBuffer buffer = new StringBuffer();
//                buffer.append(filein);
//
//                fos = new FileOutputStream(file,true);
//                pw = new PrintWriter(fos);
//                pw.write(buffer.toString());
//                pw.flush();
//
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }finally {
//                //不要忘记关闭
//                if (pw != null) {
//                    pw.close();
//                }
//                if (fos != null) {
//                    fos.close();
//                }
//                if (br != null) {
//                    br.close();
//                }
//            }
//        }
//        bool = true;
//        return bool;
//    }
//
//}
