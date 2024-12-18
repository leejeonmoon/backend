package finalproject.leejeonmoon.domain.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.common.BitMatrix;
import finalproject.leejeonmoon.domain.dto.response.DeviceInfoDto;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.service.DeviceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import finalproject.leejeonmoon.domain.dto.request.SignUpRequestDto;
import finalproject.leejeonmoon.domain.service.MemberService;
import jakarta.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.util.HashMap;
import java.util.Base64;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    private final DeviceService deviceService;

    /* 회원가입 기능 */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        memberService.signUp(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 로그아웃 기능 */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        // HttpHeaders 객체 생성하여 리다이렉션할 Location 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/login"));
        // 302 Found 상태 코드로 리다이렉트 응답 반환
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    /* 마이페이지*/
    @GetMapping("/mypage")
    public ResponseEntity<?> getMypage(){
        return ResponseEntity.ok(memberService.getMyPage());
    }
}

