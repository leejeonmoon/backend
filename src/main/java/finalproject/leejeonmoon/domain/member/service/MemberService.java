package finalproject.leejeonmoon.domain.member.service;


import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;

    //임시 메서드
    public Member getCurrentMember(){
        return memberRepository.findById((long)1).get();
    }
    public Member getMemberById(Long memberId){
        return memberRepository.findById(memberId).get();
    }
}
