package finalproject.leejeonmoon.domain.member.service;


import finalproject.leejeonmoon.domain.member.dto.SignUpRequestDto;
import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.domain.member.repository.MemberRepository;
import finalproject.leejeonmoon.global.exception.CustomException;
import finalproject.leejeonmoon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.email())) {
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.email());
        }
//        //비밀번호 일치 검사
//        if (!requestDto.password().equals(requestDto.confirmPassword())) {
//            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
//        }
        //Member 객체 생성 후 저장
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Member member = requestDto.toEntity();
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    // 10장: OAuth, 이메일을 입력받아 유저를 찾음
    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

//    public String encodePassword(String password){
//        return passwordEncoder.encode(password);
//    }
    @Transactional(readOnly = true)
    public Member getCurrentMember() throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_AUTHENTICATED));
        return member;
    }


}
