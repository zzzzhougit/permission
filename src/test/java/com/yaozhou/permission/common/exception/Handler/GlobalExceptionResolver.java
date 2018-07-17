package com.yaozhou.permission.common.exception.Handler;

import com.yaozhou.permission.common.exception.DefaultPermException;
import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.message.Result;
import com.yaozhou.permission.common.message.code.DefaultCodeMessage;
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
@ControllerAdvice
public class GlobalExceptionResolver  {

    @ExceptionHandler( value = {Exception.class} )
    public Result<?> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        //参数绑定异常
        if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            DefaultCodeMessage codeMessage = new DefaultCodeMessage(DefaultCodeMessage.CODE_ARGE_ERROR, bindException.getAllErrors().get(0).getDefaultMessage());

            return Result.error(codeMessage);

        //项目自定义异常
        } else if (exception instanceof PermException) {
            DefaultPermException permException = (DefaultPermException) exception;

            return Result.error(permException.getCodeMessage());
        }

        return Result.error(DefaultCodeMessage.SERVER_ERROR);
    }

}
