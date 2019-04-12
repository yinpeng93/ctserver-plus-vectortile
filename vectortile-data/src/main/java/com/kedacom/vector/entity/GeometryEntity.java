package com.kedacom.vector.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class GeometryEntity {
    /**
     * 形状类型— point, line, polygon, envelope
     */
    private String type;
    /**
     * 一个或多个经纬度点集合的数组
     */
    private List coordinates;

    public static GeometryEntity build(String type, List coordinates) {
        GeometryEntity shape = new GeometryEntity();
        shape.setType(type);
        shape.setCoordinates(coordinates);
        return shape;
    }
}
