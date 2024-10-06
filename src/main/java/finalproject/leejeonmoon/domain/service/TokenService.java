package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.dto.CreateAccessTokenRequestDto;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final MemberService memberService;

    // 전달받은 리프레시 토큰으로 유효성 검사 진행
    // 유효한 토큰 -> 사용자 ID를 찾음 -> 토큰 제공자의 메서드 호출하여 엑세스 토큰 생성
    public String createNewAccessToken(CreateAccessTokenRequestDto requestDto) {
        String refreshToken = requestDto.refreshToken();
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        Member member = memberService.findById(userId);

        return tokenProvider.generateToken(member, Duration.ofHours(2));
    }
}
