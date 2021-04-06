package be.technifutur.labo3.config;

import be.technifutur.labo3.security.JwtAuthenticationFilter;
import be.technifutur.labo3.security.JwtAuthorizationFilter;
import be.technifutur.labo3.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.service).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.setAllowCredentials(true);

        return request -> config;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // Connexion via le login généré par Spring Security, obligation de .permitAll() dessus, sinon elle sera inaccessible
                .formLogin().loginPage("/login").permitAll()
                .and()
                .authorizeRequests()
                // Toujours mettre en priorité les requêtes spécialisées, et après, les requêtes générales
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers("/users/**").authenticated()
                .antMatchers("/orders/**").authenticated()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()    //Gestionnaire des exceptions liées à la sécurité
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // Gestion du token JWT
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), service))
                .cors()
                .and()
                .headers().frameOptions().disable();
    }
}
