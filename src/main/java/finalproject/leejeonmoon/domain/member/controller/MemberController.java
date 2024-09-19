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
public class MemberController {
    private final MemberService memberService;

    /* 회원가입 기능 */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        // HttpHeaders 객체를 생성하여 Location 헤더에 로그인 페이지 URL 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login"));
        // 상태 코드를 302 (Found)로 설정하여 리다이렉션 처리
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    /* 로그아웃 기능 */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // SecurityContextLogoutHandler를 사용하여 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        // HttpHeaders 객체를 생성하여 Location 헤더에 로그인 페이지 URL 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login"));
        // 상태 코드를 302 (Found)로 설정하여 리다이렉션 처리
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }



}
