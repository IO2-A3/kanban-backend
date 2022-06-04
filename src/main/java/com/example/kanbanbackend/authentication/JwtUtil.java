package com.example.kanbanbackend.authentication;

import com.example.kanbanbackend.authentication.models.ExpiredTokenException;
import com.example.kanbanbackend.exceptions.ForbiddenException;
import com.example.kanbanbackend.exceptions.IncorrectIdInputException;
import com.example.kanbanbackend.exceptions.IncorrectInputDataException;
import com.example.kanbanbackend.user.UserRepository;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.*;
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

    public String extractBearerFromHeader(String header) throws Exception{
        if (header.startsWith("Bearer ")) {
            return header.substring(7);
        } else {
            throw new Exception("Invalid bearer token structure");
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final var email = userRepository.findById(UUID.fromString(extractId(token))).orElseThrow(() -> new IncorrectIdInputException("Wrong id!")).getUsername();

        if (isTokenExpired(token) || !email.equals(userDetails.getUsername())) {
            throw new ExpiredTokenException("Token has expired");
        }
        return true;
    }

    public UUID getIdFromRequest(HttpServletRequest request) throws Exception {
        String token;
        var header = request.getHeader("Authorization");

        if (Objects.isNull(header)) {
            throw new ForbiddenException();
        }

        token = extractBearerFromHeader(header);

        return UUID.fromString(extractId(token));
    }

    public Boolean validateToken(String token, HttpServletRequest httpServletRequest) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
            httpServletRequest.setAttribute("expired",ex.getMessage());
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT exception");
        }catch (IllegalArgumentException ex){
            System.out.println("Jwt claims string is empty");
        }
        return false;
    }
}
