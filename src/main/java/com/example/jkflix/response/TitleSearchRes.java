package com.example.jkflix.response;

import com.example.jkflix.tmdb.dto.PopularMovieRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleSearchRes {

    private int page;
    private List<PopularMovieRes.popularResults> results;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class popularResults { // list 형식으로 들어있는 것은 내부 클래스로 이용해준다.
        private String backdrop_path;
        private List<PopularMovieRes.Genre_ids> genre_ids;
        private int id;
        private String title;
        private String release_date;
        private Long vote_average;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Genre_ids { // list 형식으로 들어있는 것은 내부 클래스로 이용해준다.
        private int id;
    }
}
