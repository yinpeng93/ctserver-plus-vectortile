package com.kedacom.vector.module;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
}
