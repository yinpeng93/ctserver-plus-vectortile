package com.kedacom.vector.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.kedacom.vector.entity.Project;

import java.util.Map;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 15:12
 * @Description:
 */
public interface IProjectService extends IService<Project> {

    Page<Project> queryProjectList(Page<Project> page, Map<String, Object> paramMap);

    Project queryProjectByUsernameAndProjectName(String username, String projectName);

    void updateStatus(Long resourceId,Integer status);
}
