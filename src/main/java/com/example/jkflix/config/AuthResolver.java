//package com.example.jkflix.config;
//
//import com.example.jkflix.exception.AlreadyExistsEmailException;
//import com.example.jkflix.exception.BadJwtTokenException;
//import com.example.jkflix.response.SessionRes;
//import com.example.jkflix.response.UserSession;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//@Slf4j
//@RequiredArgsConstructor
//public class AuthResolver implements HandlerMethodArgumentResolver {
//
//    private final AppConfig appConfig;
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return false;
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//            // ** resolveArgument - 매개변수를 해석하고 반환하는 역할 (interceptor)
//
//            // 헤더의 Authrization 부분을 jws로 대입함
//            String jws = webRequest.getHeader("Authorization");
//            // refreshToken 호출
//            String refreshToken =
//
//        // jws 가 없을 시 오류 발생
//        if(jws == null || jws.equals("")) {
//            throw new BadJwtTokenException();
//        }
//
//        try {
//            //OK, we can trust this JWT
//            // JWT 구문 분석하고 검증
//            Jws<Claims> claims =  Jwts.parserBuilder()
//                    .setSigningKey(appConfig.getJwtKey())
//                    .build()
//                    .parseClaimsJws(jws);
//
//            String userId = claims.getBody().getSubject();
//
//            // 로그인 정보 반환
//            return new UserSession(Long.parseLong(userId));
//
//        } catch (JwtException e) {
//            //don't trust the JWT!
//            throw new BadJwtTokenException();
//        }
//
//
//    }
//}
// resolver를 이용하지 말아보자.. 