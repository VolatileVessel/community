package com.lgs.springboot.demo.DTO;

import lombok.Data;

@Data
public class GithubUser {
    private String id;
    private String name;
    private String bio;
    private String avatarUrl;

}
