package finalproject.leejeonmoon.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import finalproject.leejeonmoon.domain.member.dto.SignUpRequestDto;
import finalproject.leejeonmoon.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    /* 회원가입 기능 */
    @PostMapping("/api/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 로그아웃 기능 */
    @GetMapping("/api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        // HttpHeaders 객체 생성하여 리다이렉션할 Location 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login"));
        // 302 Found 상태 코드로 리다이렉트 응답 반환
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }



}
