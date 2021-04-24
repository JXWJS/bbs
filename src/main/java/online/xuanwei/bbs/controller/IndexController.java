package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.NotificationDTO;
import online.xuanwei.bbs.dto.PaginationDTO;
import online.xuanwei.bbs.dto.QuestionDTO;
import online.xuanwei.bbs.exception.CustomizeErrorCode;
import online.xuanwei.bbs.exception.CustomizeException;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.service.NotificationService;
import online.xuanwei.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    NotificationService notificationService;

    @GetMapping({"/","index"})
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "7") Integer size,
                        @RequestParam(name = "search",required = false) String search){
        System.out.println(page+"search"+search);
        List<QuestionDTO> questionDTOList = questionService.list(page,size);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setPages(size, questionService.getCount());

        if (page>paginationDTO.getPages()){
            throw new CustomizeException(CustomizeErrorCode.PAGE_NOT_FOUND);
        }
        if(model.getAttribute("questions")==null) {
            model.addAttribute("questions", paginationDTO);
        }else{
           // model.getAttribute("questions");
            model.addAttribute("questions",paginationDTO);

        }

        User user = (User) request.getSession().getAttribute("user");
        if (user !=null) {
            List<NotificationDTO> notificationDTOList = notificationService.getNotifier(user);
            model.addAttribute("notification", notificationDTOList);
        }
        return "index";
    }
}
