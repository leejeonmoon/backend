package finalproject.leejeonmoon.global.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") // 자바 클래스에 프로피티값을 가져와서 사용
public class JwtProperties {
    // JwtProperties: 값들을 변수로 접근하는데 사용
    private String issuer;
    private String secretKey;
}