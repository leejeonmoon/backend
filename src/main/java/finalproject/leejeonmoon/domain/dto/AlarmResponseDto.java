package finalproject.leejeonmoon.domain.dto;

import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.AlarmType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

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
