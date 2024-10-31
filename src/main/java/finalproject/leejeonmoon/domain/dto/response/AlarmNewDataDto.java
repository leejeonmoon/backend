package finalproject.leejeonmoon.domain.dto.response;

public record AlarmNewDataDto(
        Boolean isNewAlarm
) {
    public static AlarmNewDataDto from (Boolean isNewAlarm) {
        return new AlarmNewDataDto(isNewAlarm);
    }
}
