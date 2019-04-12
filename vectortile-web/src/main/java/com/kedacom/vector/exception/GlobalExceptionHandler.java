package com.kedacom.vector.exception;

import com.alibaba.fastjson.JSONException;
import com.kedacom.common.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

/**
 * 通用校验 和 controller 异常处理器  需要在使用模块的主程序入口处添加common包扫描的路径
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理IllegalArgumentException异常
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return ResponseMessage.error(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseMessage handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        return ResponseMessage.error("required_parameter_is_not_present");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败: ", e);
        return ResponseMessage.error("请求参数格式不合法");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JSONException.class)
    public ResponseMessage handleJsonException(JSONException e){
        log.error("json转换异常：",e);
        return ResponseMessage.error(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        BindingResult result = e.getBindingResult();
//        FieldError error = result.getFieldError();
//        String field = error.getField();
//        String code = error.getDefaultMessage();
//        String message = String.format("%s:%s", field, code);

        BindingResult result = e.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage()).append(";");
        }
        log.error("参数验证失败: " + builder.toString() );
        ResponseMessage response = ResponseMessage.error(builder.toString());
        return response;
    }


    /**
     *
     * 参数绑定失败
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handleBindException(BindException ex) {
        log.error("cotroller异常：参数绑定失败",ex);
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();
        String field = fieldError.getField();
        String code = fieldError.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
//        StringBuilder sb = new StringBuilder();
//        sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]");
//        sb .append(fieldError.getDefaultMessage());
        // 生成返回结果
        ResponseMessage responseMessage = ResponseMessage.error(message);
        return responseMessage;
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseMessage handleServiceException(ConstraintViolationException e) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return ResponseMessage.error("parameter:" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseMessage handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        return ResponseMessage.error("validation_exception:" + e.getMessage());
    }

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseMessage handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseMessage.error(e.getMessage());
    }


}