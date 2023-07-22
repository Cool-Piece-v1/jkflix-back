package com.example.jkflix.controller;

import com.example.jkflix.request.GenreSearchReq;
import com.example.jkflix.request.MovieDetailsReq;
import com.example.jkflix.request.TitleSearchReq;
import com.example.jkflix.tmdb.TmdbClient;
import com.example.jkflix.tmdb.dto.PopularMovieReq;
import com.example.jkflix.tmdb.service.TmdbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;

@SpringBootTest
@ComponentScan(basePackages = "com.example.jkflix") // 컴포넌트 스캔 !!
public class TmdbControllerTest {

    @Autowired
    private TmdbClient tmdbClient;

    @Autowired
    private TmdbService tmdbService;

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

        var search = new MovieDetailsReq();
        search.setContentId("667538");

        var result = tmdbClient.likeMovieDetails(search);
        System.out.println(result);
    }

    @Test
    @DisplayName("장르별 검색")
    public void searchGenreMovie() {

        var search = new GenreSearchReq();
        search.setWith_genre("공포");

        var result = tmdbService.genreSearchMovie(search);
        System.out.println(result);
    }

    @Test
    @DisplayName("제목별 검색")
    public void searchTitleMovie() {

        var search = new TitleSearchReq();
        search.setQuery("오펜하이머");

        var result = tmdbService.titleSearchMovie(search);
        System.out.println(result);

    }

}
