package finalproject.leejeonmoon.domain.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import finalproject.leejeonmoon.domain.dto.NotificationRequestDto;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.entity.Notification;
import finalproject.leejeonmoon.global.config.jwt.TokenProvider;
import finalproject.leejeonmoon.domain.repository.NotificationRepository;
import finalproject.leejeonmoon.global.exception.CustomException;
import finalproject.leejeonmoon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = memberService.getCurrentMember();
        // 해당 memberId로 이미 Notification이 존재하는지 확인
        Notification existingNotification = (Notification) notificationRepository.findByMember(member)
                .orElse(null);

        if (existingNotification != null) {
            // 기존 토큰을 새로운 토큰으로 업데이트
            existingNotification.updateToken(token);
            notificationRepository.save(existingNotification); // 변경된 내용 저장
        } else {
            // 새로운 Notification 객체를 생성하고 저장
            Notification notification = Notification.builder()
                    .token(token)
                    .build();
            notification.confirmUser(member);
            notificationRepository.save(notification);
        }
    }

//    private boolean existsByToken(String token) {
//        return notificationRepository.existsByToken(token);
//    }

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
