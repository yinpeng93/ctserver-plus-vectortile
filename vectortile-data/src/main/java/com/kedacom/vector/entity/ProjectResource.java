package com.kedacom.vector.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 11:00
 * @Description: 项目资源关系表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("project_resource")
public class ProjectResource {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 资源id
     */
    private Long resourceId;
}
