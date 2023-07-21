package com.example.jkflix.response;

import com.example.jkflix.tmdb.dto.PopularMovieRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {

    private Long id;
    private String contentId;
    private String type;
    private String userId;
    private String language;

    private List<likeDetails> likeDetail;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class likeDetails { // contentId를 통해서 맞는걸 조회하여 가져올 것은 poster_path와 title을 가져온다
        private String poster_path;
        private String title;
    }


}
