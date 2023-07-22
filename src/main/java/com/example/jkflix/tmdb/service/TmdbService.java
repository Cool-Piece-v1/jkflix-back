package com.example.jkflix.tmdb.service;

import com.example.jkflix.request.GenreSearchReq;
import com.example.jkflix.request.MovieDetailsReq;
import com.example.jkflix.request.TitleSearchReq;
import com.example.jkflix.response.GenreSearchRes;
import com.example.jkflix.response.MovieDetailsRes;
import com.example.jkflix.response.TitleSearchRes;
import com.example.jkflix.tmdb.TmdbClient;
import com.example.jkflix.tmdb.dto.PopularMovieReq;
import com.example.jkflix.tmdb.dto.PopularMovieRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TmdbService {

    private final TmdbClient tmdbClient;

    public PopularMovieRes popular(int page) {

        var search = new PopularMovieReq();
        search.setLanguage("ko-KR");
        search.setPage(page);
        search.setRegion("KR");

        return tmdbClient.popularMovie(search);
    }

    public MovieDetailsRes searchContentDetail(String likeContentId) {

        var search = new MovieDetailsReq();
        search.setContentId(likeContentId);

        return tmdbClient.likeMovieDetails(search);

    }


    public GenreSearchRes genreSearchMovie(GenreSearchReq genreSearchReq) {

        Map<String, String> genreList = new HashMap<>();

        genreList.put("액션", "28");
        genreList.put("모험", "12");
        genreList.put("애니메이션", "16");
        genreList.put("코미디", "35");
        genreList.put("범죄", "80");
        genreList.put("다큐멘터리", "99");
        genreList.put("드라마", "18");
        genreList.put("가족", "10751");
        genreList.put("판타지", "14");
        genreList.put("역사", "36");
        genreList.put("공포", "27");
        genreList.put("음악", "10402");
        genreList.put("미스터리", "9648");
        genreList.put("로맨스", "10749");
        genreList.put("SF", "878");
        genreList.put("TV영화", "10770");
        genreList.put("스릴러", "53");
        genreList.put("전쟁", "10752");
        genreList.put("서부", "37");

        var search = new GenreSearchReq();
        search.setWith_genre(genreList.get(genreSearchReq.getWith_genre()));


        return tmdbClient.genreSearchMovie(search);


    }

    public TitleSearchRes titleSearchMovie(TitleSearchReq titleSearchReq) {

        var search = new TitleSearchReq();
        search.setQuery(titleSearchReq.getQuery());

        return tmdbClient.titleSearchMovie(search);

    }
}
