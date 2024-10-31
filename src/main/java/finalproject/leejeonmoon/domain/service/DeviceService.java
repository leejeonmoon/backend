package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.entity.Device;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.dto.response.DeviceInfoDto;
import finalproject.leejeonmoon.domain.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final MemberService memberService;
    private final DeviceRepository deviceRepository;

    public void registerDevice(DeviceInfoDto deviceInfo) {
        Member member = memberService.getCurrentMember();
        Device device = new Device(deviceInfo.getDdnsUrl(), member);
        deviceRepository.save(device);
    }
}
