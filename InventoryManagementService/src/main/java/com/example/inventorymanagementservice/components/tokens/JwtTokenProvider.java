package com.example.inventorymanagementservice.components.tokens;

import com.example.inventorymanagementservice.components.persistence.entities.User;
import com.example.inventorymanagementservice.components.persistence.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Component
//@ComponentScan("com.example.inventorymanagementservice.components.tokens")
public class JwtTokenProvider {
    public static Logger logger = LogManager.getLogger("com.example.inventorymanagementservice");

    @Autowired
    private JwtKeyProvider jwtKeyProvider;
    @Autowired
    private UserRepository userRepository;

//    @Value("${jwt.expirationTime}")
    @Value("3600000")
    private long expirationTime;

    public String generateToken(User user) {
        try {
            SecretKey signingKey = jwtKeyProvider.getSigningKey();
            Claims claims = Jwts.claims().setSubject(user.getUsername());
            claims.put("role", user.getRole());
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expirationTime);
            return Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("role", user.getRole())
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(signingKey, SignatureAlgorithm.HS256)
                    .compact();
        }
        catch (Exception e)
        {
            logger.error("Error generating token "+e);
            throw new RuntimeException("Error generating token!!");
        }
    }
    public User getUserFromToken(String token) {
        logger.info("Going to introspect token");
        try {
            SecretKey signingKey = jwtKeyProvider.getSigningKey();
            Claims claims = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token)
                    .getBody();
            String userName = claims.getSubject();
            Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByUsername(userName));
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            logger.error("Error introspecting token " + e);
            throw new RuntimeException("Invalid token!!");
        }
    }

}