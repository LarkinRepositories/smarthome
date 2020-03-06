//package ru.innopolis.zuul.config;//package ru.innopolis.authService.config;
//
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.servlet.http.HttpServletResponse;
//
//import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {
//    @Qualifier("jwtUserDetailsService")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtConfig jwtConfig;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig))
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
//                .anyRequest().authenticated();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public JwtConfig jwtConfig() {
//        return new JwtConfig();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STRICT)
//                .setFieldMatchingEnabled(true)
//                .setSkipNullEnabled(true)
//                .setFieldAccessLevel(PRIVATE);
//        return mapper;
//    }
//}
