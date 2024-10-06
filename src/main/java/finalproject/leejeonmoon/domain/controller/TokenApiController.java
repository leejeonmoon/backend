package finalproject.leejeonmoon.domain.controller;

import finalproject.leejeonmoon.domain.dto.CreateAccessTokenRequestDto;
import finalproject.leejeonmoon.domain.service.TokenService;
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
