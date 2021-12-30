package com.example.kanbanbackend.authentication;

import com.example.kanbanbackend.authentication.models.ExpiredTokenException;
import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.IncorrectInputDataException;
import com.example.kanbanbackend.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@AllArgsConstructor
public class JwtUtil {
    private final String SECRET_KEY = "dupa";
    private final UserRepository userRepository;

    public String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails, Integer time) {
        Map<String, Object> claims = new HashMap<>();
        var user = userRepository.findUserByUsername(userDetails.getUsername()).get();
        return createToken(claims, user.getId().toString(), time);
    }

    private String createToken(Map<String, Object> claims, String subject, Integer time) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final var username = userRepository.findById(UUID.fromString(extractId(token))).orElseThrow(() -> new IncorrectIdInputException("Wrong id!")).getUsername();
        if (isTokenExpired(token)) {
            throw new ExpiredTokenException("Token has expired");
        } else if (!username.equals(userDetails.getUsername())) {
            throw new IncorrectInputDataException("Wrong username! expected: " + username + " acquired " + userDetails.getUsername());
        }
        return true;
    }
}
