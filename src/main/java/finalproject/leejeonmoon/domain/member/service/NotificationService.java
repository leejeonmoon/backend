package finalproject.leejeonmoon.domain.member.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import finalproject.leejeonmoon.domain.member.dto.NotificationRequestDto;
import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.domain.member.entity.Notification;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import finalproject.leejeonmoon.domain.member.repository.NotificationRepository;
import finalproject.leejeonmoon.global.exception.CustomException;
import finalproject.leejeonmoon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @Transactional
    public void saveNotification(String token) {
//        Member member = memberService.findByEmail(tokenProvider.getLoginUserEmail(token));
        Member member = memberService.getCurrentMember();
        Notification notification = Notification.builder()
                .token(token)
                .build();

        notification.confirmUser(member);
        notificationRepository.save(notification);
    }

    public String getNotificationToken() {
        Member member = memberService.getCurrentMember();
        Notification notification = (Notification) notificationRepository.findByMember(member)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return notification.getToken();
    }

    public void sendNotification(NotificationRequestDto req) throws ExecutionException, InterruptedException {
        String token = getNotificationToken();

        Message message = Message.builder()
                .setWebpushConfig(WebpushConfig.builder()
                        .setNotification(WebpushNotification.builder()
                                .setTitle(req.title())
                                .setBody(req.message())
                                .build())
                        .build())
                .setToken(token)
                .build();
        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        log.info(">>>>Send message : " + response);
    }
}
