package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 사용자 이름(email)로 사용자의 정보를 가져오는 메서드
    @Override
    public Member loadUserByUsername(String email){
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
