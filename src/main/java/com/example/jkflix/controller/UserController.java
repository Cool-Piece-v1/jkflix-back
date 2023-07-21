package com.example.jkflix.controller;

import com.example.jkflix.config.AppConfig;
import com.example.jkflix.request.Login;
import com.example.jkflix.request.Signup;
import com.example.jkflix.response.SessionResponse;
import com.example.jkflix.response.UserResponse;
import com.example.jkflix.response.UserSession;
import com.example.jkflix.service.Userservice;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final Userservice userservice;

    private final AppConfig appConfig;

    @PostMapping("/api/v1/auth/sign-up")
    public void signup(@RequestBody Signup signup) {
        userservice.signup(signup);
    }

    @PostMapping("/api/v1/auth/log-in")
    public SessionResponse login(@RequestBody Login login) {
        // SessionResponse (response) ==> accessToken을 String으로 내려줌
        // Login(request) : Email , password

        // user마다 독립된 번호를 내려줘야 하기 때문에 userId를 가져옴
        Long userId = userservice.signin(login);

        // JWT 토큰에 사용할 비밀키 가져옴
        SecretKey key = Keys.hmacShaKeyFor(appConfig.getJwtKey());

        // 액세스 토큰 설정
        String jws = Jwts.builder()
                .setSubject(String.valueOf(userId)) // 해당 사용자 식별
                .signWith(key) // 비밀키 서명
                .setIssuedAt(new Date()) // 토큰 발급 시간 (요청시 마다 바뀜)
                .compact();

        return new SessionResponse(jws); // accessToken 형식의 json 형식으로 잔달
    }

    @GetMapping("api/v1/user/me/{id}")
    public ResponseEntity<Map<String, Object>> getMe(@PathVariable Long id) {
        // 해야할 것, 현재 내 정보를 json으로 반환해줘야함
        return ResponseEntity.ok().body(userservice.getMyData(id));
    }
}
