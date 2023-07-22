package com.example.jkflix.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreSearchReq {

    private String with_genre;

    private String language = "ko-KR"; // default

    private int page = 1; // default

    private String sort_by = "popularity.desc"; // default



    // 쿼리파라미터를 한번에 만들어주기 위함
    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("language", language);
        map.add("page", String.valueOf(page));
        map.add("sort_by", sort_by);
        map.add("with_genres", with_genre);

        return map;
    }

}
