package rs.raf.domaci6lazarbojanic11621rn.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import rs.raf.domaci6lazarbojanic11621rn.model.ServiceUser;

public class TokenService {

    private static String jwtSecret = "NQu2mzEtCwrNaJCjsoHT";

    public static String MASTER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsInBhc3MiOiJhZG1pbiJ9.EcbsD0Wn1wkI8iVVTEOX0IWHuwyqOndzPUFtDAM4TMI";
    public String generate(ServiceUser serviceUser) {
        Claims claims = Jwts.claims();
        claims.put("id", serviceUser.getId());
        claims.put("username", serviceUser.getUsername());
        claims.put("pass", serviceUser.getPass());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims parseToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
        }
        catch (Exception e) {
            return null;
        }
        return claims;
    }
}