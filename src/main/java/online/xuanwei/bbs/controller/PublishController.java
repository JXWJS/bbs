package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.exception.CustomizeErrorCode;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.exception.ICustomizeErrorCode;
import online.xuanwei.bbs.mapper.QuestionMapper;
import online.xuanwei.bbs.mapper.QuestionMapperExt;
import online.xuanwei.bbs.model.Category;
import online.xuanwei.bbs.model.Question;
import online.xuanwei.bbs.model.QuestionExample;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.service.CategoryService;
import online.xuanwei.bbs.util.Result;
import online.xuanwei.bbs.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PublishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionMapperExt questionMapperExt;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/publish/edit")
    public String edit(@RequestParam(name="id") Integer id, Model model){
       Question question =  questionMapper.selectByPrimaryKey(id);
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
       model.addAttribute("edit",question);
        return "publish";
    }

    @GetMapping("/publish_update")
    public  String update(@RequestParam("id") Integer id,
                          @RequestParam("title") String title,
                          @RequestParam("description") String description,
                          @RequestParam("tag") String tag,
                          @RequestParam("selectEdit") Integer selectEdit){
      Question question = new Question();
      question.setId(id);
      question.setTitle(title);
      question.setDescription(description);
      question.setTag(tag);
      question.setGmtModified(System.currentTimeMillis());
      question.setCategoryId(selectEdit);
      int updated = questionMapperExt.updateContentByPrimaryKey(question);
      if (updated !=1){
          throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      }
          return "redirect:question?id=" + id;

    }

    @GetMapping("/publish")
    public  String publish(Model model){
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        return "publish";
    }

    @GetMapping("/doPublish")
    @ResponseBody
    public Result doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam("category") Integer categoryId,
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
            question.setCategoryId(categoryId);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
            return  ResultGenerator.genSuccessResult("发布成功");
        }

    }
}
