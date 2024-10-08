package finalproject.leejeonmoon.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmType {
    SNORT("SNORT 알림입니다."),
    FIREWALL("방화벽 알림입니다."),
    INTERFACE("사용자 인터페이스 알림입니다."),
    TEST("테스트 알림입니다.");

    private final String messagePattern;
}
