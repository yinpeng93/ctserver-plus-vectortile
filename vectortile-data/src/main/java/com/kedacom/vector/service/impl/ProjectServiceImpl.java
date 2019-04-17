package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.Project;
import com.kedacom.vector.entity.ProjectResource;
import com.kedacom.vector.mapper.ProjectMapper;
import com.kedacom.vector.service.IProjectResourceService;
import com.kedacom.vector.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 15:13
 * @Description:
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper,Project> implements IProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private IProjectResourceService projectResourceService;

    @Override
    public Page<Project> queryProjectList(Page<Project> page, Map<String, Object> paramMap) {

        List<Project> projectList = projectMapper.queryProjectList(page,paramMap);
        return page.setRecords(projectList);
    }

    @Override
    public Project queryProjectByUsernameAndProjectName(String username, String projectName) {
        return selectOne(Condition.create().eq("username",username).eq("project_name",projectName));
    }

    @Override
    public void updateStatus(Long resourceId,Integer status) {
        projectMapper.updateStatus(resourceId,status);
    }

    @Override
    @Transactional
    public void deleteProject(String projectId) {
        //根据projectid删除project
        delete(Condition.create().eq("project_id",projectId));

        //根据projectid删除project_resource
        projectResourceService.delete(Condition.create().eq("project_id",projectId));
    }

    @Override
    public List<Project> queryProjectList(Long resourceId) {
        return projectMapper.queryProjectListByResourceId(resourceId);
    }
}
