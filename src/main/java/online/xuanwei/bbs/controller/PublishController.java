package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.mapper.QuestionMapper;
import online.xuanwei.bbs.model.Question;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.util.Result;
import online.xuanwei.bbs.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;


    @GetMapping("/publish")
    public  String publish(){
        return "publish";
    }

    @GetMapping("/doPublish")
    @ResponseBody
    public Result doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest httpServletRequest
                             ){
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if(user == null){

            return ResultGenerator.genFailResult("请您登录再发布");
        }else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
            return  ResultGenerator.genSuccessResult("发布成功");
        }

    }
}
