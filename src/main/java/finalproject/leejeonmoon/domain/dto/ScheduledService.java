package finalproject.leejeonmoon.domain.dto;

import finalproject.leejeonmoon.domain.dto.response.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledService {
    private final NotificationService notificationService;

    @Scheduled(cron = "0 40 12 * * ?")
    public void scheduledSend() throws ExecutionException, InterruptedException {
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .title("leejeonmoon")
                .token(notificationService.getNotificationToken())
                .message("접속할 시간입니다!")
                .build();
        notificationService.sendNotification(notificationRequestDto);
        log.info("보내졌습니다!");
    }
}
