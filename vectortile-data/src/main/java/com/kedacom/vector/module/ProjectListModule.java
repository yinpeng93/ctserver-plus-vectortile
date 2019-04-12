package com.kedacom.vector.module;

import lombok.Data;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 17:33
 * @Description:
 */
@Data
public class ProjectListModule {
    private String projectName;

    private String startTime;

    private String endTime;

    private String adminId;

    private Integer pageNo;

    private Integer pageSize;
}
