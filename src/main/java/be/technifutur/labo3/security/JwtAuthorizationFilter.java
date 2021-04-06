package be.technifutur.labo3.security;

import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JWTAuthenticationFilter.JWTConstants.HEADER_STRING);

        if (token != null && token.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    // Récupération de l'utilisateur (via son username, inclus dans le payload du token)
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.JWTConstants.SECRET_KEY))
                .build()
                .verify(token.replace("Bearer ", ""))
                .getSubject()
                ;

        if(username != null) {
            User u = (User) this.userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(u.getUsername(), null, u.getAuthorities());
        }

        return null;
    }


}
