package org.gym.servet.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.gym.servet.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {

    private static final long EXPIRE_TIME =  60 * 60 * 24 ; // token过期时间为24小时

    public String getToken(String userId, String password) {

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        return JWT.create().withAudience(userId)
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(password));

    }

        public String getAdminToken(String AdminId, String password) {

            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            return JWT.create().withAudience(AdminId)
                    .withExpiresAt(date)
                    .sign(Algorithm.HMAC256(password));







}
}
