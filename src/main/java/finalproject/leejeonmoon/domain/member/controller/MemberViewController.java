package finalproject.leejeonmoon.domain.member.controller;

import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;


@Controller
public class MemberViewController {
    private final TokenProvider tokenProvider;

    public MemberViewController(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
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

//    @GetMapping("/oauthIndex")
//    public String oauthIndex(Model model, @AuthenticationPrincipal Member member){
//        model.addAttribute("nickname", member.getNickname());
//        return "oauthIndex";
//    }

    @GetMapping("/oauthIndex")
    public String oauthIndex(@RequestParam(value = "token", required = false) String token) {
        // 토큰이 있을 경우 처리
        if (token != null && tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "oauthIndex"; // 정상적으로 페이지로 이동
    }



}
