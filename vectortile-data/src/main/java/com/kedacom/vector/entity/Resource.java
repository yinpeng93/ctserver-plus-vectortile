package com.kedacom.vector.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 10:22
 * @Description: 资源表,具体已经或者正在生产的切片资源
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resource")
public class Resource {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 数据范围，行政区域编码
     */
    private String dataScope;

    /**
     * 坐标系
     */
    private String coordinateSystem;

    /**
     * 生产状态  0.正在处理  1.已完成   2.处理失败
     */
    private Integer status;
}
