package com.example.showmeyourability.shared.Service;

import com.example.showmeyourability.shared.Exception.HttpException;
import com.example.showmeyourability.users.domain.User;
import com.example.showmeyourability.users.infrastructure.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SecurityService {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    private final UserRepository userRepository;

    public String createToken(String email) {

        if (EXPIRATION_TIME <= 0) {
            throw new RuntimeException("Expiratio time must be greater than zero!");
        }

        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public User getSubject(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return userRepository.findByEmail(claims.getSubject())
                .orElseThrow(
                        () -> new HttpException(
                                false,
                                "존재하지 않은 사용자입니다.",
                                HttpStatus.NOT_FOUND
                        )
                );
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
