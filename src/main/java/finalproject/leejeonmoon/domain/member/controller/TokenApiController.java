package finalproject.leejeonmoon.domain.member.controller;

import finalproject.leejeonmoon.domain.member.dto.CreateAccessTokenRequestDto;
import finalproject.leejeonmoon.domain.member.dto.CreateAccessTokenResponseDto;
import finalproject.leejeonmoon.domain.member.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public ResponseEntity<?> createNewAccessToken(@RequestBody CreateAccessTokenRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tokenService.createNewAccessToken(requestDto));
    }
}
