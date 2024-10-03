package finalproject.leejeonmoon.domain.member.repository;

import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.domain.member.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long>  {
    Optional<Object> findByMember(Member member);

    boolean existsByToken(String token);
}
