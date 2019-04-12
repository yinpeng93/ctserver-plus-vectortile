package com.kedacom.vector.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yinpeng
 * @since 2019-02-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String pinyin;

    private String adminId;

    private String relationNames;

    private String relationAdminId;


}
