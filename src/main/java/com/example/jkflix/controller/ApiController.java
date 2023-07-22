package com.example.jkflix.controller;

import com.example.jkflix.request.GenreSearchReq;
import com.example.jkflix.request.TitleSearchReq;
import com.example.jkflix.response.GenreSearchRes;
import com.example.jkflix.response.TitleSearchRes;
import com.example.jkflix.tmdb.dto.PopularMovieRes;
import com.example.jkflix.tmdb.service.TmdbService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/jkflix")
@RequiredArgsConstructor
public class ApiController {

    private final TmdbService tmdbService;

    @GetMapping("/main") // 메인 화면에 먼저 인기있는 것을 보여줄 예정
    public PopularMovieRes popular(@RequestParam(defaultValue = "1") int page) {
        return tmdbService.popular(page);
    }

    @GetMapping("/genreSearch")
    public GenreSearchRes genreSearchMovie(GenreSearchReq genreSearchReq) {
        return tmdbService.genreSearchMovie(genreSearchReq);
    }

    @GetMapping("/titleSearch")
    public TitleSearchRes genreSearchMovie(TitleSearchReq titleSearchReq) {
        return tmdbService.titleSearchMovie(titleSearchReq);
    }




}
