package finalproject.leejeonmoon.domain.dto;

import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.AlarmType;

import java.time.LocalDateTime;

public record SseUnitDto(
        Boolean isRead,
        String title,
        String content,
        AlarmType alarmType,
        LocalDateTime createdTime
) {
    public static SseUnitDto from(Alarm alarm, String content) {
        return new SseUnitDto(
                alarm.getIsRead(),
                alarm.getTitle(),
                content,
                alarm.getType(),
                alarm.getCreatedTime()
        );
    }
}
