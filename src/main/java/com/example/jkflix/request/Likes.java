package com.example.jkflix.request;

import lombok.Data;

@Data
public class Likes {

    private int contentId;

    private String type;

    // Signup의 id와 조인해야함
    private int userId;


}
