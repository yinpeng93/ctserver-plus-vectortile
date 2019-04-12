package com.kedacom.common.response;

/*
 *
 *  * Copyright 2016 http://www.kedacomframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */



import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Sets;
import com.kedacom.common.enums.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

/**
 * 响应消息。controller中处理后，返回此对象，响应请求结果给客户端。
 *
 * @author xuwei
 */
@ApiModel(description = "响应结果")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> implements Serializable{

    private static final long serialVersionUID = 8992436576262574064L;

    @ApiModelProperty("错误消息")
    @Getter
    @Setter
    protected String message;

    @ApiModelProperty("成功时响应内容")
    @Getter
    @Setter
    protected T result;

    @ApiModelProperty(value = "状态码", required = true)
    @Getter
    @Setter
    protected int status;

    @ApiModelProperty(value = "业务自定义状态码")
    @Getter
    @Setter
    protected String code;

    @ApiModelProperty(value = "响应内容的字段")
    @Getter
    @Setter
    protected LinkedHashSet<String> fields;

    @ApiModelProperty(value = "时间戳", required = true, dataType = "Long")
    @Getter
    @Setter
    protected Long timestamp;

    /**
     * 过滤字段：指定需要序列化的字段
     */
    @Getter
    @ApiModelProperty(hidden = true)
    protected transient Map<Class<?>, Set<String>> includes;

    /**
     * 过滤字段：指定不需要序列化的字段
     */
    @Getter
    @ApiModelProperty(hidden = true)
    protected transient Map<Class<?>, Set<String>> excludes;


    public static <T> ResponseMessage<T> error(String message) {
        return error(500, CodeEnum.UNKNOWN.getValue(), message);
    }

    public static <T> ResponseMessage<T> error(String message, Object... args) {
        return error(500, message, args);
    }

    public static <T> ResponseMessage<T> error(int status, String message) {
        return error(status, CodeEnum.UNKNOWN.getValue(), message);
    }

    public static <T> ResponseMessage<T> error(int status, String message, Object... args) {
        return error(status, CodeEnum.UNKNOWN.getValue(), message, args);
    }

    public static <T> ResponseMessage<T> error(int status, String code, String message) {
        return error(status, code, message, null);
    }

    public static <T> ResponseMessage<T> error(int status, String code, String message, Object... args) {
        ResponseMessage<T> msg = new ResponseMessage<>();
        if (!StringUtils.isEmpty(message))
        {
            msg.message = MessageFormat.format(message, args);
        }
        msg.status(status);
        msg.code(code);
        return msg.putTimeStamp();
    }

    public static <T> ResponseMessage<T> ok() {
        return ok(null);
    }

    private ResponseMessage<T> putTimeStamp() {
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public static <T> ResponseMessage<T> ok(T result) {
        return new ResponseMessage<T>()
                .result(result)
                .putTimeStamp()
                .code(CodeEnum.SUCCESS.getValue())
                .status(200);
    }

    public ResponseMessage<T> result(T result) {
        this.result = result;
        return this;
    }

    public ResponseMessage() {

    }

    public ResponseMessage<T> include(Class<?> type, String... fields) {
        return include(type, Arrays.asList(fields));
    }

    public ResponseMessage<T> include(Class<?> type, Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        include(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(includes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage<T> exclude(Class type, Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        fields.forEach(field -> {
            if (field.contains(".")) {
                String tmp[] = field.split("[.]", 2);
                try {
                    Field field1 = type.getDeclaredField(tmp[0]);
                    if (field1 != null) {
                        exclude(field1.getType(), tmp[1]);
                    }
                } catch (Throwable e) {
                }
            } else {
                getStringListFromMap(excludes, type).add(field);
            }
        });
        return this;
    }

    public ResponseMessage<T> exclude(Collection<String> fields) {
        if (excludes == null) {
            excludes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (getResult() != null) {
            type = getResult().getClass();
        } else {
            return this;
        }
        exclude(type, fields);
        return this;
    }

    public ResponseMessage<T> include(Collection<String> fields) {
        if (includes == null) {
            includes = new HashMap<>();
        }
        if (fields == null || fields.isEmpty()) {
            return this;
        }
        Class type;
        if (getResult() != null) {
            type = getResult().getClass();
        } else {
            return this;
        }
        include(type, fields);
        return this;
    }

    public ResponseMessage<T> exclude(Class type, String... fields) {
        return exclude(type, Arrays.asList(fields));
    }

    public ResponseMessage<T> exclude(String... fields) {
        return exclude(Arrays.asList(fields));
    }

    public ResponseMessage<T> include(String... fields) {
        return include(Arrays.asList(fields));
    }

    protected Set<String> getStringListFromMap(Map<Class<?>, Set<String>> map, Class type) {
        return map.computeIfAbsent(type, k -> new HashSet<>());
    }

    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss");
    }

    public ResponseMessage<T> status(int status) {
        this.status = status;
        return this;
    }

    public ResponseMessage<T> code(String code) {
        this.code = code;
        return this;
    }

    public ResponseMessage<T> fields(LinkedHashSet<String> fields) {
        this.fields = fields;
        return this;
    }

    public ResponseMessage<T> field(String field) {
        if (this.fields == null) {
            synchronized (this) {
                if (this.fields == null) {
                    this.fields = Sets.newLinkedHashSet();
                }
            }
        }
        this.fields.add(field);
        return this;
    }

}