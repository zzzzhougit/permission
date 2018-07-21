package com.yaozhou.permission.configure;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.message.Result;
import com.yaozhou.permission.common.message.entity.CodeMessage;
import com.yaozhou.permission.common.message.entity.ExceptionEntity;
import com.yaozhou.permission.common.message.entity.ViewMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * @author Yao.Zhou
 * @since 2018/7/17 22:34
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionResolver  {

    @ExceptionHandler( value = {Exception.class} )
    public Object exceptionHandler(Model model, HttpServletRequest request, HttpServletResponse response, Exception exception) {
        ExceptionEntity exceptionEntity = null;

        //参数绑定异常
        if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            exceptionEntity = new CodeMessage(CodeMessage.CODE_ARGE_ERROR, bindException.getAllErrors().get(0).getDefaultMessage());

        //项目自定义异常
        } else if (exception instanceof PermException) {
            PermException permException = (PermException) exception;
            log.error(permException.getExceptionEntity().getMessage(), exception);
            exceptionEntity = permException.getExceptionEntity();

        //其他未定义异常统一是服务器异常
        } else {
            log.error(exception.getMessage(), exception);
            exceptionEntity = CodeMessage.SERVER_ERROR;
        }

        if (exceptionEntity instanceof ViewMessage) {
            model.addAttribute("codeMessage", exceptionEntity);
            //TODO path?

            return model;
        } else {

            return Result.error(exceptionEntity);
        }
    }

}
