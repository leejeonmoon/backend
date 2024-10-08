package finalproject.leejeonmoon.domain.controller;

import finalproject.leejeonmoon.domain.service.AlarmService;
import finalproject.leejeonmoon.domain.service.EmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmApiController {
    private AlarmService alarmService;
    private final EmitterService emitterService;

    /* 알림 목록 조회 */
    @GetMapping
    public ResponseEntity<?> getAlarms(){
        return ResponseEntity.ok(alarmService.getAlarmList());
    }

    /* 알림 구독 */
    @GetMapping(value = "/api/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe() {
        return ResponseEntity.ok(emitterService.subscribe());
    }

//    /* 알림 목록 조회 및 읽음 처리 */
//    @PatchMapping
//    public ResponseEntity<?> getRecentAlarms() {
//        return ResponseEntity.ok(alarmService.getAndReadRecentAlarms());
//    }

}
