package com.ryan.hello.dto;


import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;


}
