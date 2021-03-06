package com.lgs.springboot.demo.controller;

import com.lgs.springboot.demo.dto.AccessTokenDTO;
import com.lgs.springboot.demo.dto.GithubUser;
import com.lgs.springboot.demo.mapper.UserMapper;
import com.lgs.springboot.demo.model.User;
import com.lgs.springboot.demo.provider.GithubProvider;
import com.lgs.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clentSecret;
    @Value("${github.clent.uri}")
    private String clentUri;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    //接收code和state的
    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    public String callback(@RequestParam(name = "code") String code ,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clentSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clentUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //把user的name显示到登陆状态里
        if (githubUser !=null && githubUser.getId()!=null){
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            /*user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());*/
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            //userMapper.insert(user);
            //写入cookie
            response.addCookie(new Cookie("token",token));
           // request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie =new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


}
