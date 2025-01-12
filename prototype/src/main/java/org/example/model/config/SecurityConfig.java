package org.example.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disablează CSRF pentru testare (poți să-l activezi dacă folosești sesiuni)
                .authorizeRequests()
                .antMatchers("/api/auth/login", "/api/auth/register").permitAll() // Permite accesul public pentru login și register
                .anyRequest().authenticated()  // Restul endpoint-urilor necesită autentificare
                .and()
                .formLogin().disable()  // Dezactivează autentificarea bazată pe formulare (folosim REST)
                .logout()
                .logoutUrl("/api/auth/logout") // Setăm endpoint-ul de logout
                .invalidateHttpSession(true)   // Invalidează sesiunea
                .clearAuthentication(true)    // Șterge informațiile de autentificare
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
