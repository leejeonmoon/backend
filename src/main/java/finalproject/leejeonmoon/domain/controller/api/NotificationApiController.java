package finalproject.leejeonmoon.domain.controller.api;

import finalproject.leejeonmoon.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationApiController {
    private final NotificationService notificationService;

    @PostMapping("/new")
    public void saveNotification(@RequestBody String token) {
        notificationService.saveNotification(token);
    }

//    @GetMapping
//    public String notificationPage(Model model) {
//        model.addAttribute("firebase.apiKey", "YOUR_API_KEY");
//        model.addAttribute("firebase.authDomain", "YOUR_AUTH_DOMAIN");
//        model.addAttribute("firebase.projectId", "YOUR_PROJECT_ID");
//        model.addAttribute("firebase.storageBucket", "YOUR_STORAGE_BUCKET");
//        model.addAttribute("firebase.messagingSenderId", "YOUR_MESSAGING_SENDER_ID");
//        model.addAttribute("firebase.appId", "YOUR_APP_ID");
//        model.addAttribute("firebase.vapidKey", "YOUR_VAPID_KEY"); // Optional, if needed
//
//        return "notification";
//    }


}
