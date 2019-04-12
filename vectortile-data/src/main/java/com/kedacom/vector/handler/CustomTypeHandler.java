package com.kedacom.vector.handler;

import com.alibaba.fastjson.JSON;
import com.kedacom.vector.entity.GeometryEntity;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({GeometryEntity.class})
public class CustomTypeHandler implements TypeHandler<GeometryEntity> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, GeometryEntity geometryEntity, JdbcType
            jdbcType) throws SQLException {
        // TODO  此处后面可能需要修改代码  需要注意的是 sql中需要将geojson转换成text String 如：st_astext('0101000020E6100000F4893C49BA745E40D49AE61DA7B83D40')
        preparedStatement.setObject(i,
                JSON.toJSONString(geometryEntity));
    }

    @Override
    public GeometryEntity getResult(ResultSet resultSet, String s) throws SQLException {
        return JSON.parseObject(resultSet.getString(s),GeometryEntity.class);
    }

    @Override
    public GeometryEntity getResult(ResultSet resultSet, int i) throws SQLException {
        return JSON.parseObject(resultSet.getString(i),GeometryEntity.class);
    }

    @Override
    public GeometryEntity getResult(CallableStatement callableStatement, int i) throws SQLException {
        return JSON.parseObject(callableStatement.getString(i),GeometryEntity.class);
    }
}
