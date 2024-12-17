package finalproject.leejeonmoon.domain.repository;

import finalproject.leejeonmoon.domain.entity.Device;
import finalproject.leejeonmoon.domain.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
//    Optional<Device> findByDeviceCode(String deviceCode);
//    void deleteByDeviceCode(String deviceCode);
    List<Device> findAllByMember(Member member);
}

