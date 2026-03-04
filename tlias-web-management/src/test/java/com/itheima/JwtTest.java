package com.itheima;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap=new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aXRoZWltYQ==")
                .addClaims(dataMap) //添加自定义数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置过期时间
                .compact();//生成令牌
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3MjU0MDU4MX0.mfvshL8iBPqWrCbm__7Dxrp5OMh6FWZ8l7y0Ni3cue4";
        Claims claims =Jwts.parser()
                .setSigningKey("aXRoZWltYQ==")
                .parseClaimsJws(token)
                .getBody();//获取令牌自定义信息
        System.out.println(claims);
    }
}
