package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.PaginationDTO;
import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    QuestionService questionService;

    User user;



    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest httpServletRequest,
                         @PathVariable(name = "action") String action , Model model,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "7") Integer size){
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            user = (User)httpServletRequest.getSession().getAttribute("user");
            List<QuestionDTO> questionDTOList = questionService.getMYQuestionDTOList(user.getId(),page, size);
            PaginationDTO paginationDTO = new PaginationDTO();
            paginationDTO.setQuestionDTOList(questionDTOList);
            paginationDTO.setPage(page);
            paginationDTO.setSize(size);
            paginationDTO.setPages(size, questionService.getMyCount(user.getId()));
            if (model.getAttribute("myQuestions") != null) {
                model.getAttribute("myQuestions");
            }
           model.addAttribute("myQuestions", paginationDTO);

        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }


        return "profile";
    }



}
