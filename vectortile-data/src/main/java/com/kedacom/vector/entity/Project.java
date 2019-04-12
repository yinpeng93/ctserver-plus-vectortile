package com.kedacom.vector.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 11:35
 * @Description: 生产切片项目配置
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Project {
    /**
     * 自增id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 数据范围，即adminId
     */
    private String dataScope;

    /**
     * 坐标系 "gcj02" "wgs84"
     */
    private String coordinateSystem;

    /**
     * 0:亮色系    1：暗色系
     */
    private Integer loadStyle;

    /**
     * 状态  0.正在处理  1.已完成   2.处理失败
     */
    private Integer status;

    /**
     * 提交时间
     */
    private Date createTime;
}
