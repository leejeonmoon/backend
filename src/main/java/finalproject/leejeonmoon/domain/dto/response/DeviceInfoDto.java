package finalproject.leejeonmoon.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceInfoDto {
    @JsonProperty("ddnsUrl")
    private String ddnsUrl;

    @JsonProperty("macAddress")
    private String macAddress;

    public DeviceInfoDto(String ddnsUrl, String macAddress) {
        this.ddnsUrl = ddnsUrl;
        this.macAddress = macAddress;
    }
}
