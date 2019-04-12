package com.kedacom.vector.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kedacom.vector.entity.ProjectResource;
import com.kedacom.vector.mapper.ProjectResourceMapper;
import com.kedacom.vector.service.IProjectResourceService;
import org.springframework.stereotype.Service;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/26 0026 11:07
 * @Description:
 */
@Service
public class ProjectResourceServiceImpl extends ServiceImpl<ProjectResourceMapper,ProjectResource> implements IProjectResourceService {
}
