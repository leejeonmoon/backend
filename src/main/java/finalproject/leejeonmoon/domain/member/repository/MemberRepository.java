package finalproject.leejeonmoon.domain.member.repository;

import finalproject.leejeonmoon.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
