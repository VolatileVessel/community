package com.lgs.springboot.demo.controller;

import com.lgs.springboot.demo.DTO.AccessTokenDTO;
import com.lgs.springboot.demo.DTO.GithubUser;
import com.lgs.springboot.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientid;
    @Value("${github.client.secret}")
    private String clentSecret;
    @Value("${github.clent.uri}")
    private String clentUri;
    //接收code和state的
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code ,
                            @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clentSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clentUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }


}
