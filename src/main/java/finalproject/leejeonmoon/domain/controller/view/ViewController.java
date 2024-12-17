package finalproject.leejeonmoon.domain.controller.view;

import finalproject.leejeonmoon.domain.entity.Device;
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
import java.util.Date;
import java.util.List;

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
    public String streaming(Model model) {
        Member member = memberService.getCurrentMember();
        List<Device> devices = deviceService.findDevicesByMember(member);

        model.addAttribute("devices", devices);
        if (!devices.isEmpty()) {
            Device device = devices.get(0);
            model.addAttribute("deviceId", device.getDeviceId().toString());
            model.addAttribute("ddnsUrl", device.getDdnsUrl());
        }

        return "streaming";
    }
    @Controller
    public class VideoController {
        @GetMapping("/video")
        public String getVideos(Model model) {
            // 날짜 정보 추가
            model.addAttribute("currentDate", new Date());

            return "video";
        }
    }

    @GetMapping("/statistics")
    public String statistics() {
        return "statistics";
    }

    @GetMapping("/mypage")
    public String mypage(Model model) {
        Member member = memberService.getCurrentMember();
        List<Device> devices = deviceService.findDevicesByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("devices", devices);
        return "mypage";
    }
    @GetMapping("/qr-scanner")
    public String qrScanner() {
        return "qrcode";  // templates/qrscanner.html을 가리킴
    }

//    @GetMapping("/oauthIndex")
//    public String oauthIndex(Model model, @AuthenticationPrincipal Member member){
//        model.addAttribute("nickname", member.getNickname());
//        return "oauthIndex";
//    }


//    @GetMapping("/oauthIndex")
//    public String oauthIndex(@RequestParam(value = "token", required = false) String token) {
//        // 토큰이 있을 경우 처리
//        if (token != null && tokenProvider.validToken(token)) {
//            Authentication authentication = tokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        return "oauthIndex"; // 정상적으로 페이지로 이동
//    }

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


//    @GetMapping("/authenticated")
//    public String authenticated(Model model) {
//        // 인증 여부 확인
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("Authentication object: {}", authentication);
//        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
//            // 인증되지 않은 사용자는 로그인 페이지로 리다이렉트
//            return "redirect:/login";
//        }
//        // 인증된 사용자의 정보를 Model에 추가하여 Thymeleaf 템플릿에서 사용 가능
//        String username = authentication.getName();
//        model.addAttribute("username", username);
//        return null;
//    }

//    @GetMapping("/authenticated")
//    public String checkAuthentication() {
//        // 현재 사용자의 인증 정보 가져오기
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info(""+authentication);
//        // 인증 확인
//        if (authentication != null && authentication.isAuthenticated() &&
//                !(authentication instanceof AnonymousAuthenticationToken)) {
//            // UsernamePasswordAuthenticationToken이면 인증된 사용자로 간주
//            return null;
//        } else {
//            log.info("인증되지 않은 사용자");
//            // AnonymousAuthenticationToken이거나 인증되지 않은 경우
//            return "redirect:/login";
//        }
//    }


}
