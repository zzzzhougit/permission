package com.yaozhou.permission.configure.resolver;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.common.result.entity.CodeMessage;
import com.yaozhou.permission.common.result.entity.PermEntity;
import com.yaozhou.permission.common.result.entity.ViewMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * @author Yao.Zhou
 * @since 2018/7/17 22:34
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionResolver  {

    @Autowired
    protected ThymeleafViewResolver thymeleafViewResolver;

    @ExceptionHandler( value = {Exception.class} )
    public Object exceptionHandler(Model model, HttpServletRequest request, HttpServletResponse response, Exception exception) {
        PermEntity exceptionEntity = null;

        //参数绑定异常
        if (exception instanceof BindException) {
            BindException bindException = (BindException) exception;
            exceptionEntity = new CodeMessage(CodeMessage.CODE_ARGE_ERROR, bindException.getAllErrors().get(0).getDefaultMessage());

        //项目自定义异常
        } else if (exception instanceof PermException) {
            PermException permException = (PermException) exception;
            exceptionEntity = permException.getExceptionEntity();

            if (log.isDebugEnabled()) {
                log.error(permException.getExceptionEntity().getMessage(), exception);
            }

        //其他未定义异常统一是服务器异常
        } else {
            exceptionEntity = CodeMessage.SERVER_ERROR;

            log.error(exception.getMessage(), exception);
        }

        if (exceptionEntity instanceof ViewMessage) {
            ViewMessage viewMessage = (ViewMessage) exceptionEntity;
            model.addAttribute("codeMessage", exceptionEntity);

            return renderViewPage(request, response, model, viewMessage.getPath());
        } else {

            return Result.error(exceptionEntity);
        }
    }

    private String renderViewPage(HttpServletRequest request, HttpServletResponse response, Model model, String path) {
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());

        return thymeleafViewResolver.getTemplateEngine().process(path, webContext);
    }

}
