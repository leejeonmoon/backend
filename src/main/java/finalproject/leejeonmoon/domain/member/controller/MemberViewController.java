package finalproject.leejeonmoon.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberViewController {
    @GetMapping("/login")
    public String login() {
        return "oauthLogin"; // 10장: oauthlogin으로 변경
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // -> signup.html 반환
    }

    @GetMapping("/index.html")
    public String index() {
        return "index";
    }
}
