package com.kedacom.vector.dto;

import com.kedacom.vector.entity.GeometryEntity;
import lombok.Data;

/**
 * @Auther: YinPeng
 * @Date: 2019/2/21 0021 17:31
 * @Description:
 */
@Data
public class Feature<T> {
    private String type = "Feature";

    private Integer id;

    private T properties;

    private GeometryEntity geometry;
}
