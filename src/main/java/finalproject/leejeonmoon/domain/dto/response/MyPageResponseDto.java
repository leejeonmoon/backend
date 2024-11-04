package finalproject.leejeonmoon.domain.dto.response;

import finalproject.leejeonmoon.domain.entity.Device;
import finalproject.leejeonmoon.domain.entity.Member;
import java.util.List;

public record MyPageResponseDto(
    String email,
    String nickname,
    List<Device> memberDeviceList
) {
    public MyPageResponseDto from(Member member, List<Device> memberDeviceList) {
        return new MyPageResponseDto(
            member.getNickname(),
            member.getEmail(),
            memberDeviceList
        );
    }

}
