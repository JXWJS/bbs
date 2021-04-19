package online.xuanwei.bbs.advice;

import com.alibaba.fastjson.JSON;
import okio.Utf8;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.util.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object Model(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Throwable throwable, Model model) throws IOException {
            if(throwable instanceof CustomizeException && "application/json".equals(httpServletRequest.getContentType().split(";")[0])){
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF8");
                PrintWriter printWriter = httpServletResponse.getWriter();
                printWriter.write(JSON.toJSONString(ResultGenerator.genFailResult(throwable.getMessage())));
                printWriter.close();
                return  null;
        }else{
                HttpStatus httpStatus = getStatus(httpServletRequest);
                if (throwable instanceof CustomizeException) {
                    model.addAttribute("message", throwable.getMessage());
                }
                return  new ModelAndView("error");
            }
    }

    private HttpStatus getStatus(HttpServletRequest httpServletRequest){
        Integer statusCode = (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(statusCode);
    }
}