package finalproject.leejeonmoon.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceInfoDto {
    private String ddnsUrl; // 라즈베리파이의 DDNS URL

    public DeviceInfoDto(String ddnsUrl) {
        this.ddnsUrl = ddnsUrl;
    }
}
