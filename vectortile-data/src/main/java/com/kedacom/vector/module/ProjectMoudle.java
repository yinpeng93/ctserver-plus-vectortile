package com.kedacom.vector.module;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 14:53
 * @Description: 生产切片页面对应的域
 */
@Data
public class ProjectMoudle {
    /**
     * 项目名称
     */
    @NotNull(message = "projectName为空，项目名称不能为空")
    private String projectName;

    /**
     * 邮箱
     */
    @NotNull(message = "email为空，邮箱不能为空")
    private String email;

    /**
     * 数据范围
     */
    @NotNull(message = "adminId为空，必须选择所在区域")
    private String adminId;

    /**
     * 账户名
     */
    private String username;

    /**
     * 坐标系   "gcj02" "wgs84"
     */
    @NotNull(message = "coordinateSystem为空，必须选择坐标系")
    private String coordinateSystem;

    /**
     * 0 亮色系，1 暗色系
     */
    @NotNull(message = "loadStyle为空，必须选择加载样式")
    private Integer loadStyle;

    /**
     * 是否生产mbtiles,0 不生产mbtiles，只生产geojson     1 生产mbtiles和geojson
     */
    @NotNull(message = "mode为空，必须填写mode")
    private Integer mode = 1;

    @Size(min = 2,max = 2,message = "r1ZoomLevel是r1的区间，由两个integer类型值框定区间")
    private List r1ZoomLevel = Arrays.asList(5,19);

    @Size(min = 2,max = 2,message = "r21ZoomLevel是r21的区间，由两个integer类型值框定区间")
    private List r21ZoomLevel = Arrays.asList(10,19);

    @Size(min = 2,max = 2,message = "r22ZoomLevel是r22的区间，由两个integer类型值框定区间")
    private List r22ZoomLevel = Arrays.asList(12,19);

    @Size(min = 2,max = 2,message = "r31ZoomLevel是r31的区间，由两个integer类型值框定区间")
    private List r31ZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "r32ZoomLevel是r32的区间，由两个integer类型值框定区间")
    private List r32ZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "r33ZoomLevel是r33的区间，由两个integer类型值框定区间")
    private List r33ZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "r34ZoomLevel是r34的区间，由两个integer类型值框定区间")
    private List r34ZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "poiZoomLevell是poi的区间，由两个integer类型值框定区间")
    private List poiZoomLevel = Arrays.asList(10,19);

    @Size(min = 2,max = 2,message = "buildingZoomLevel是building的区间，由两个integer类型值框定区间")
    private List buildingZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "residentialZoomLevel是residential的区间，由两个integer类型值框定区间")
    private List residentialZoomLevel = Arrays.asList(15,19);

    @Size(min = 2,max = 2,message = "tZoomLevel是t的区间，由两个integer类型值框定区间")
    private List tZoomLevel = Arrays.asList(5,17);

    @Size(min = 2,max = 2,message = "dtZoomLevel是dt的区间，由两个integer类型值框定区间")
    private List dtZoomLevel = Arrays.asList(5,7);

    @Size(min = 2,max = 2,message = "bpZoomLevel是bp的区间，由两个integer类型值框定区间")
    private List bpZoomLevel = Arrays.asList(5,19);

    @Size(min = 2,max = 2,message = "dZoomLevel是d的区间，由两个integer类型值框定区间")
    private List dZoomLevel = Arrays.asList(5,19);

    @Size(min = 2,max = 2,message = "hamletZoomLevel是hamlet的区间，由两个integer类型值框定区间")
    private List hamletZoomLevel = Arrays.asList(5,10);
}
