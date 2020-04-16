package dreamteam.db_project.config;

import dreamteam.db_project.security.JwtAuthenticationEntryPoint;
import dreamteam.db_project.security.JwtAuthenticationFilter;
import dreamteam.db_project.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final
    AuthService authService;

    public SecurityConfig(AuthService authService) {
        this.authService = authService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/**",
                        "/csrf",
                        "/webjars/**")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/managements").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/new_product_journal").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/orders").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/payment_types").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/price_journals").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/product_category").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/product_units").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/quantity_journals").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/roles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/sold_products").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/stuffs").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/suppliers").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/z_reports").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}
