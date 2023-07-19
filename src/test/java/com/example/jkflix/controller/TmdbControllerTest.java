package com.example.jkflix.controller;

import com.example.jkflix.tmdb.TmdbClient;
import com.example.jkflix.tmdb.dto.PopularMovieReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TmdbControllerTest {

    @Autowired
    private TmdbClient tmdbClient;

    @Test
    public void searchPopularMovies() {

        var search = new PopularMovieReq();
        search.setLanguage("ko-KR");
        search.setPage(1);
        search.setRegion("KR");

        var result = tmdbClient.popularMovie(search);
        System.out.println(result);
    }


}
