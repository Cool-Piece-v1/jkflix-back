package com.example.jkflix.controller;

import com.example.jkflix.response.LikeResponse;
import com.example.jkflix.response.MovieDetails;
import com.example.jkflix.tmdb.TmdbClient;
import com.example.jkflix.tmdb.dto.PopularMovieReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TmdbClient.class)
public class TmdbControllerTest {

    @Autowired
    private TmdbClient tmdbClient;

    @Test
    @DisplayName("인기영화 리스트업")
    public void searchPopularMovies() {

        // 인기영화 리스트업 ==> tmdb 테스트용으로 먼저 만듦
        
        var search = new PopularMovieReq();
        search.setLanguage("ko-KR");
        search.setPage(1);
        search.setRegion("KR");

        var result = tmdbClient.popularMovie(search);
        
        System.out.println(result);
    }



    @Test
    @DisplayName("라이크에 쓸 영화 리스트업")
    public void likeMovieDetail() {

        var search = new MovieDetails();
        search.setContnenId("667538");

        var result = tmdbClient.likeMovieDetails(search);
        System.out.println(result);
    }

}
