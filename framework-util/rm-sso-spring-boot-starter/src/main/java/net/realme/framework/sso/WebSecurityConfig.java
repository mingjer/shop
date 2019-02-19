package net.realme.framework.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Author: 91000156
 * @Date: 2018/8/24 11:13
 * @Description:
 */
@Configuration
@EnableWebSecurity
@AutoConfigureAfter(SsoAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    private static final String[] STATICS_LIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/login",
            "/logout",
    };

    /**
     *  bypass all the security
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                .antMatchers(STATICS_LIST)
                .antMatchers(ssoConfigProperties.getIgnoreList() != null ? ssoConfigProperties.getIgnoreList() : new String[0]);
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .requestCache()
                .requestCache(new NullRequestCache())
                .and()
                .logout().permitAll().invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
//                .accessDeniedHandler(new AccessDeniedHandler())
                .and()
                .addFilterAt(preAuthenticatedFilter(), AbstractPreAuthenticatedProcessingFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthProvider());
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new ColorOSLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new ColorOSAuthenticationEntryPoint();
    }

    @Bean
    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
        return new ColorOSAuthenticationUserDetailsService();
    }

    @Bean
    public ColorOSAuthenticationFilter preAuthenticatedFilter() throws Exception {
        ColorOSAuthenticationFilter colorOSAuthenticationFilter = new ColorOSAuthenticationFilter();
        colorOSAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        colorOSAuthenticationFilter.setAuthenticationSuccessHandler(new ColorOSAuthSuccessHandler());
        return colorOSAuthenticationFilter;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider preAuthProvider =  new PreAuthenticatedAuthenticationProvider();
        preAuthProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
        return preAuthProvider;
    }
}
