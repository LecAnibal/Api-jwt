package com.asm.apiexpress.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTGenerate {

    public String getToken(String username) {
        String secretKey = "mySecretKey";
        String permission = "ADMIN";

        List grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(permission);

        String token = Jwts
                .builder()
                .setId("LECANIBAL_JWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(it -> {
                                    return new SimpleGrantedAuthority(it.toString());
                                })
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
