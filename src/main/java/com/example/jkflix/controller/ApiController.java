package com.example.jkflix.controller;

import com.example.jkflix.tmdb.dto.PopularMovieRes;
import com.example.jkflix.tmdb.service.TmdbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/jkflix")
@RequiredArgsConstructor
public class ApiController {

    private final TmdbService tmdbService;

    @GetMapping("/main") // 메인 화면에 먼저 인기있는 것을 보여줄 예정 ==> 무한 스크롤 ?
    public PopularMovieRes popular() {
        return tmdbService.popular();
    }





}
