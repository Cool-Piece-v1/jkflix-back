package com.example.jkflix.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Base64;

@Data
@ConfigurationProperties(prefix = "kwonkwon")
public class AppConfig {

    // String이던 secret key를 바이트형식으로 변환해주는 클래스
    // yml 파일에서 kwonkwon에 들어있는 값을 가져옴
    // 1. 기존 코드의 중복성 방지
    // 2. 매번 byte[] 로 변환해줘야하는 번거로움 해소
    
    private byte[] jwtKey;

    public void setJwtKey(String jwtKey) {
        this.jwtKey = Base64.getDecoder().decode(jwtKey);
    }

    public byte[] getJwtKey() {
        return jwtKey;
    }

}
