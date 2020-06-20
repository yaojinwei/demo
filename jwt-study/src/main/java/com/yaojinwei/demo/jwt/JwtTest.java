package com.yaojinwei.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class JwtTest {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder()
                .setId("888")   //设置唯一编号
                .setSubject("小白")//设置主题  可以是JSON数据
                .setIssuedAt(new Date())//设置签发日期
                .signWith(SignatureAlgorithm.HS256, "hahaha");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        String compactJwt=builder.compact();

        System.out.println(compactJwt);

        builder = Jwts.builder()
                .setId("888")   //设置唯一编号
                .setSubject("小白11")//设置主题  可以是JSON数据
                .setIssuedAt(new Date())//设置签发日期
                .signWith(SignatureAlgorithm.HS256, "hahaha");//设置签名 使用HS256算法，并设置SecretKey(字符串)
        compactJwt=builder.compact();

        System.out.println(compactJwt);

        Claims claims = Jwts.parser().setSigningKey("hahaha").parseClaimsJws(compactJwt).getBody();
        System.out.println(claims);

//构建 并返回一个字符串
    }
}
