
## Quick Start

Here are some key points listed, the complete example

```xml
<!--add dependency in pom.xml-->
<dependency>
    <groupId>net.realme</groupId>
    <artifactId>rm-sso-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Usage

>  鉴权接口配置项，由ColorOS基础团队提供

```yaml
coloros:
  auth.id: xxx
  auth.key: xxx
  auth.secret: xxx
  auth.host: http://www
  auth.login-url: http://www
```


> sample configuration for Spring Security


```java
@Configuration
@EnableWebSecurity
@AutoConfigureAfter(SsoAutoConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] STATICS_LIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
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
                .logout().permitAll()
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
        return colorOSAuthenticationFilter;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider preAuthProvider =  new PreAuthenticatedAuthenticationProvider();
        preAuthProvider.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService());
        return preAuthProvider;
    }
}
```

