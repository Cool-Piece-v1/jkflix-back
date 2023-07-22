package com.example.jkflix.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsRes {

    private Long id;
    private String poster_path;
    private String title;
    private String overview;
    private String language = "ko-KR";

}
