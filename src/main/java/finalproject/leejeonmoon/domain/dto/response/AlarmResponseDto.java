package finalproject.leejeonmoon.domain.dto.response;

import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.AlarmType;

import java.time.LocalDateTime;

public record AlarmResponseDto(
        Long alarmId,
        AlarmType type,
        String title,
        String content,
        LocalDateTime createdDate
) {

    public static AlarmResponseDto from(Alarm alarm) {
        return new AlarmResponseDto(
                alarm.getAlarmId(),
                alarm.getType(),
                alarm.getTitle(),
                alarm.getContent(),
                alarm.getCreatedDate()
        );
    }

}
