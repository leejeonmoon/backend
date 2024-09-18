package finalproject.leejeonmoon.domain.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("page", "home");
        model.addAttribute("logged_in", false);
        return "index"; // index.html을 반환
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("page", "login");
        model.addAttribute("logged_in", false);
        return "login"; // login.html을 반환
    }

    @GetMapping("/streaming")
    public String streaming(Model model) {
        model.addAttribute("page", "streaming");
        model.addAttribute("logged_in", true);
        return "streaming"; // streaming.html을 반환
    }

    @GetMapping("/video")
    public String video(Model model) {
        model.addAttribute("page", "video");
        model.addAttribute("logged_in", true);
        return "video"; // video.html을 반환
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("page", "report");
        model.addAttribute("logged_in", true);
        return "report"; // report.html을 반환
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html을 반환
    }
}

