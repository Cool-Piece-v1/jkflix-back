package com.example.jkflix.tmdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularMovieReq {

    // ** 요청할 때 사용함

    private String language = ""; // default

    private int page = 1; // default

    private String region = "KR"; // default

    // 쿼리파라미터를 한번에 만들어주기 위함
    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("language", language);
        map.add("page", String.valueOf(page));
        map.add("region", region);

        return map;
    }




}
