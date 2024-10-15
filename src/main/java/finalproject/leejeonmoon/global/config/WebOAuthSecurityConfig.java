package finalproject.leejeonmoon.global.config;

import lombok.RequiredArgsConstructor;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import finalproject.leejeonmoon.global.config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import finalproject.leejeonmoon.global.config.oauth.OAuth2SuccessHandler;
import finalproject.leejeonmoon.global.config.oauth.OAuth2UserCustomService;
import finalproject.leejeonmoon.domain.repository.RefreshTokenRepository;
import finalproject.leejeonmoon.domain.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {
    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    @Bean
    public WebSecurityCustomizer configure() {
        // 스프링 시큐리티 기능 비활성화
        return (web) -> web.ignoring()
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:8080", "https://api.leejeonmoon.p-e.kr"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF, HTTP Basic, 폼 로그인, 로그아웃 비활성화 (토큰 인증 방식 사용)
        http
//                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .logout(logout -> logout.disable());


        // 세션을 사용하지 않고, Stateless 방식으로 설정
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 커스텀 필터 추가 (헤더를 확인하는 필터)
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 인증 설정
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/firebase-messaging-sw.js").permitAll()
                        .requestMatchers("/alarms/**", "/api/**", "/notification/**", "/authenticated","/oauthIndex", "/video","/streaming", "/api/token", "/", "/index", "/signup", "/login").permitAll() // 인증 없이 접근 가능
                        .requestMatchers("/streaming").authenticated() // 로그인한 사용자만 접근 가능
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요 (이 부분은 필요에 따라 추가 가능)
        );

        // OAuth2 로그인 설정
        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login")
//                .authorizationEndpoint(authorization -> authorization
//                        .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
//                )
                .redirectionEndpoint(redirection -> redirection.baseUri("/login/oauth2/code/**"))
                .successHandler(oAuth2SuccessHandler())
                .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserCustomService))
        );

        // 로그아웃 성공 시 리다이렉트 설정
        http.logout(logout -> logout
                .logoutSuccessUrl("/login")
        );

        // 인증되지 않은 사용자가 접근할 경우 401 상태 코드 반환
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        new AntPathRequestMatcher("/**") // 모든 요청에 대해 인증되지 않은 경우
                )
        );


        return http.build();
    }

    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                memberService) {
        };
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
