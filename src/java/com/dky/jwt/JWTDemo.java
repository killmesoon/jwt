package com.dky.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class JWTDemo {

    public String createToken() {
        String secret = "secret";//token 密钥
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","jwt");
        Date nowDate = new Date();
        Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0); //2小时过期

        String token = JWT.create()
                .withHeader(map)
                .withIssuer("SERVICE")
                .withSubject("this is test token")//签名的主题
                //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                .withAudience("APP")//签名的观众 也可以理解谁接受签名的
                .withIssuedAt(nowDate) //生成签名的时间
                .withExpiresAt(expireDate)//签名过期的时间
                /*签名 Signature */
                .sign(algorithm);
                return token;

    }


    public String createTokenWithClaim() {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,0,0,0,2,0,0);//2小过期
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim("loginName", "lijunkui")
                    .withIssuer("SERVICE")//签名是有谁生成 例如 服务器
                    .withSubject("this is test token")//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience("APP")//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);
            return token;
    }



    /**
     * 设置过期时间
     * @param date
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if(date == null){
            date = new Date();
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

    public void verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("SERVICE")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        String subject = jwt.getSubject();
        Map<String, Claim> claims = jwt.getClaims();
        Claim claim = claims.get("loginName");
        System.out.println(claim.asString());
        List<String> audience = jwt.getAudience();
        System.out.println(subject);
        System.out.println(audience.get(0));

    }


    public static void main(String[] args) {
        JWTDemo demo = new JWTDemo();
        String tokenWithClaim = demo.createTokenWithClaim();
        System.out.println(tokenWithClaim);
//        demo.verifyToken(tokenWithClaim);
    }
}
