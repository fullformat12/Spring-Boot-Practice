package bimatlaptrinh.com.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@Setter
public class JwtService {
    private String accessSecret;
    private String refreshSecret;
    private long accessExpMinutes;
    private long refreshExpDays;

    public String generateAccessToken(UserDetails user) {
        return buildToken(user, accessSecret,
                Duration.ofMinutes(accessExpMinutes));
    }
    public String generateRefreshToken(UserDetails user) {
        return buildToken(user, refreshSecret,
                Duration.ofDays(refreshExpDays));
    }

    private String buildToken(UserDetails user, String key, Duration ttl) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ttl.toMillis()))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key)),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token, boolean isRefresh) {
        return parser(isRefresh).parseClaimsJws(token)
                .getBody().getSubject();
    }
    public boolean isExpired(String token, boolean isRefresh) {
        Date exp = parser(isRefresh).parseClaimsJws(token)
                .getBody().getExpiration();
        return exp.before(new Date());
    }
    private JwtParser parser(boolean isRefresh) {
        String key = isRefresh ? refreshSecret : accessSecret;
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key)))
                .build();
    }
}