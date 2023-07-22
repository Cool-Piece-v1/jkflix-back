package com.example.jkflix.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserSession {

    public final Long id;

    public UserSession(Long id) {
        this.id = id;
    }


}
