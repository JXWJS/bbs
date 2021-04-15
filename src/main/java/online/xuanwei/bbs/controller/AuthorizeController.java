package online.xuanwei.bbs.controller;

import online.xuanwei.bbs.dto.AccessTokenDTO;
import online.xuanwei.bbs.dto.GithubUser;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.model.UserExample;
import online.xuanwei.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clienId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;



    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clienId);
        accessTokenDTO.setClient_secret(clientSecret);
        System.out.println(accessTokenDTO.toString());
        String accessToken = githubProvider.getAccessToken(accessTokenDTO
        );
        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);

            if(githubUser.getId() !=0){
                UserExample userExample = new UserExample();
                userExample.createCriteria()
                        .andAccountIdEqualTo(Long.toString(githubUser.getId()));
                List<User> users = userMapper.selectByExample(userExample);
                if(users.size()  == 0 ) {
                User user = new User();
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setName(githubUser.getName());
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setBio(githubUser.getBio());
                user.setAvatarUrl(githubUser.getAvatar_url());
                userMapper.insert(user);
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(60 * 60 * 24 * 20);
                response.addCookie(cookie);
                request.getSession().setAttribute("user", user);
                return "redirect:index";
            }else {
                User user = new User();
                user = users.get(0);
                user.setToken(UUID.randomUUID().toString());
                userMapper.updateByExample(user,userExample);
                Cookie cookie = new Cookie("token", user.getToken());
                cookie.setMaxAge(60 * 60 * 24 * 20);
                response.addCookie(cookie);
                request.getSession().setAttribute("user", user);
                return "redirect:index";
            }
        }else{
            return "redirect:index";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        httpServletRequest.getSession().removeAttribute("user");
        return "redirect:index";
    }
}
