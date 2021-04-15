package online.xuanwei.bbs.advice;

import online.xuanwei.bbs.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView Model(HttpServletRequest httpServletRequest, Throwable throwable, Model model){
        HttpStatus httpStatus = getStatus(httpServletRequest);
        if (throwable instanceof CustomizeException) {
            model.addAttribute("message", throwable.getMessage());
        }
        return  new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest httpServletRequest){
        Integer statusCode = (Integer) httpServletRequest.getAttribute("javax.servlet.error.status_code");
        if(statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return  HttpStatus.valueOf(statusCode);
    }
}