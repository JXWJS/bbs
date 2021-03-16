package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import sun.invoke.empty.Empty;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    GithubUser user = new GithubUser();

    @GetMapping({"/","index"})
    public String index(HttpServletRequest request){
        if(request.getSession().getAttribute("user")==null){
            request.getSession().setAttribute("user",user);
        }
        System.out.println(request.getSession().getAttribute("user").toString());
        return "index";
    }
}
