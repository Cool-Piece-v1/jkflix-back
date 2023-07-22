package com.example.jkflix.controller;

import com.example.jkflix.request.GenreSearchReq;
import com.example.jkflix.response.GenreSearchRes;
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


    @GetMapping("/genre-serch")
    public GenreSearchRes genreSearchRes(@RequestParam(defaultValue = "1") int page, GenreSearchReq genreSearchReq) {
        // 장르를 서치할 건데, 기준은 popular 에 나온 정보들을 기준으로 불러들일거야
        return tmdbService.genreSearchMoive(page, genreSearchReq);
    }



}
