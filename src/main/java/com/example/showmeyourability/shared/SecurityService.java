package com.example.showmeyourability.shared;

import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SecurityService {

//    properties 로 빼기
    private static final String SECRET_KEY ="dlfkajsdflkajsf;laskdjf;lasdkjfads;lkfjasd;lkfjasdfklasjdflaskjdhflsjadhf";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private final UserRepository userRepository;

    public String createToken(String subject) {
//        token 에 대해서 공부하기
//        security 를 도입
        System.out.println("createToken subject"+subject);
        if (EXPIRATION_TIME <= 0) {
            throw new RuntimeException("Expiratio time must be greater than zero!");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(signingKey,signatureAlgorithm)
                .compact();
    }

    public User getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return userRepository.findByEmail(claims.getSubject()).orElseThrow();
    }

    public String getTokenByCookie(Cookie[] cookies) {
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        return token;
    }
}
