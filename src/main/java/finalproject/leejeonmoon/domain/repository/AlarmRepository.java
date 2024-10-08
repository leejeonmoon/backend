package finalproject.leejeonmoon.domain.repository;


import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByMember(Member member);
    List<Alarm> findByReceiverOrderByCreatedTimeDesc(Member receiver);
    Boolean existsByReceiverAndIsReadFalse(Member member);
    void deleteAllBySender(Member member);
    void deleteAllByReceiver(Member member);
}
