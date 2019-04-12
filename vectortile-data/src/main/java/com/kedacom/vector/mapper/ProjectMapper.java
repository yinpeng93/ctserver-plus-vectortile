package com.kedacom.vector.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kedacom.vector.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 15:08
 * @Description:
 */
public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> queryProjectList(Page<Project> page, Map<String, Object> paramMap);

    void updateStatus(@Param("resourceId") Long resourceId, @Param("status") Integer status);
}
