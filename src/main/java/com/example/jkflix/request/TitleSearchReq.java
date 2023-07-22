package com.example.jkflix.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleSearchReq {

    private String query;
    private String language = "ko-KR";
    private int page = 1 ;

    // 쿼리파라미터를 한번에 만들어주기 위함
    public MultiValueMap<String, String> toMultiValueMap() {
        var map = new LinkedMultiValueMap<String, String>();

        map.add("language", language);
        map.add("page", String.valueOf(page));
        map.add("query", query);

        return map;
    }
}
