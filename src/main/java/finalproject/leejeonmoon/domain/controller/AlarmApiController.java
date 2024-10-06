package finalproject.leejeonmoon.domain.controller;

import finalproject.leejeonmoon.domain.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarms")
@RequiredArgsConstructor
public class AlarmApiController {
    private AlarmService alarmService;

    /* 알림 목록 조회 */
    @GetMapping
    public ResponseEntity<?> getAlarms(){
        return ResponseEntity.ok(alarmService.getAlarmList());
    }
}
