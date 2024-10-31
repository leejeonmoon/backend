package finalproject.leejeonmoon.domain.controller.view;

import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.service.DeviceService;
import finalproject.leejeonmoon.domain.service.MemberService;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class ViewController {
    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final DeviceService deviceService;

    public ViewController(TokenProvider tokenProvider, MemberService memberService,
        DeviceService deviceService) {
        this.tokenProvider = tokenProvider;
        this.memberService = memberService;
        this.deviceService = deviceService;
    }

    @GetMapping("/login")
    public String login() {
        return "oauthLogin"; // 10장: oauthlogin으로 변경
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // -> signup.html 반환
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/streaming")
    public String streaming() {
        return "streaming";
    }

    @GetMapping("/video")
    public String video() {
        return "video";
    }

    @GetMapping("/oauthIndex")
    public String oauthIndex(@RequestParam(value = "token", required = false) String token, Model model) {
        // 토큰이 있을 경우 처리
        if (token != null && tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        Member member = memberService.getCurrentMember();
        model.addAttribute("nickname", member.getNickname());
        return "oauthIndex"; // 정상적으로 페이지로 이동
    }

    @GetMapping("/authenticated")
    public ResponseEntity<String> authenticated(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            // 인증되지 않은 사용자는 401 상태 코드로 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        // 인증된 사용자의 이름을 반환
        String username = authentication.getName();
        return ResponseEntity.ok(username); // 상태 200과 함께 사용자 이름 반환
    }

    @GetMapping("/notification")
    public String notificationPage(Model model) {
        model.addAttribute("firebase.apiKey", "YOUR_API_KEY");
        model.addAttribute("firebase.authDomain", "YOUR_AUTH_DOMAIN");
        model.addAttribute("firebase.projectId", "YOUR_PROJECT_ID");
        model.addAttribute("firebase.storageBucket", "YOUR_STORAGE_BUCKET");
        model.addAttribute("firebase.messagingSenderId", "YOUR_MESSAGING_SENDER_ID");
        model.addAttribute("firebase.appId", "YOUR_APP_ID");
        model.addAttribute("firebase.vapidKey", "YOUR_VAPID_KEY"); // Optional, if needed

        return "notification";
    }
//
//    @GetMapping("/qr")
//    public String generateqr() {
//        return "qr";
//    }

//    /* QR 코드 생성 페이지 */
//    @GetMapping("/generate")
//    public String generateQrPage(@RequestParam("deviceCode") String deviceCode, Model model) {
//        model.addAttribute("deviceCode", deviceCode);
//        return "generate";
//    }
//
//    /* 기기 등록 완료 페이지 */
//    @GetMapping("/registration-success")
//    public String registrationSuccessPage() {
//        return "registration-success";
//    }

}
