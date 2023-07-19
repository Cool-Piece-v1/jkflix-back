package com.example.jkflix.tmdb;

import com.example.jkflix.tmdb.dto.PopularMovieReq;
import com.example.jkflix.tmdb.dto.PopularMovieRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TmdbClient {

    @Value("${tmdb.client.api-Key}")
    private String ApiKey;

    @Value("${tmdb.client.authorization}")
    private String ApiAccessToken;

    @Value("${tmdb.url.popular.movie}")
    private String tmdbPopularMovieUrl;

    @Value("${tmdb.url.search.movie}")
    private String tmdbDiscoverMovieUrl;


    public PopularMovieRes popularMovie(PopularMovieReq popularMovieReq) {

        // uri 세팅
        var uri = UriComponentsBuilder.fromUriString(tmdbPopularMovieUrl)
                .queryParams(popularMovieReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        // header 세팅
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " +  "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmNjhiYWFmOTIwZjM0MTE5YmY5MTcwZjgyM2UwMmY1NiIsInN1YiI6IjY0YWJiM2YzNmEzNDQ4MDEyY2U1Y2UxNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5ncFIDAh7Cr9kOybYI2eyizPhADxboe23pk6L4ip4MQ");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Entity에 담아줌
        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<PopularMovieRes>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return responseEntity.getBody();

    }


}
