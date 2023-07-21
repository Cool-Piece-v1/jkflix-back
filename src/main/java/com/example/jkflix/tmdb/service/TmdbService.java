package com.example.jkflix.tmdb.service;

import com.example.jkflix.request.MovieDetailsReq;
import com.example.jkflix.response.MovieDetailsRes;
import com.example.jkflix.tmdb.TmdbClient;
import com.example.jkflix.tmdb.dto.PopularMovieReq;
import com.example.jkflix.tmdb.dto.PopularMovieRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TmdbService {

    private final TmdbClient tmdbClient;

    public PopularMovieRes popular() {

        var search = new PopularMovieReq();
        search.setLanguage("ko-KR");
        search.setPage(1);
        search.setRegion("KR");

        return tmdbClient.popularMovie(search);
    }

    public MovieDetailsRes searchContentDetail(String likeContentId) {

        var search = new MovieDetailsReq();
        search.setContentId(likeContentId);

        return tmdbClient.likeMovieDetails(search);

    }
}
