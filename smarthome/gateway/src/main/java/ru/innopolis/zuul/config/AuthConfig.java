package ru.innopolis.zuul.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.innopolis.zuul.security.jwt.JwtTokenConfigurer;
import ru.innopolis.zuul.security.jwt.JwtTokenProvider;


import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthConfig(@Lazy JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/login", "/api/user/add/").permitAll()
//                .antMatchers("/api/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtTokenConfigurer(jwtTokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/*/")
         web.ignoring()
//                 .antMatchers("/api/auth/**", "/api/auth/user/**")
//                 .antMatchers("/api/user/**")
                 .antMatchers("/web/**", "/web/resources/**", "/web/static/**")
                 .antMatchers("/api/device/**")
                 .antMatchers("/api/scenario/**")
                 .antMatchers("/api/mqtt/**")
                 .antMatchers("/api/rest-out/**")
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }
}
