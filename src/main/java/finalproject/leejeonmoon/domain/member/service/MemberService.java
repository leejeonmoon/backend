package finalproject.leejeonmoon.domain.member.service;


import finalproject.leejeonmoon.domain.member.dto.SignUpRequestDto;
import finalproject.leejeonmoon.domain.member.entity.Member;
import finalproject.leejeonmoon.domain.member.repository.MemberRepository;
import finalproject.leejeonmoon.global.exception.CustomException;
import finalproject.leejeonmoon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequestDto requestDto) {
        if (existsByEmail(requestDto.email())) {
            throw new IllegalArgumentException("이미 존재하는 email입니다." + requestDto.email());
        }
        //비밀번호 일치 검사
        if (!requestDto.password().equals(requestDto.confirmPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        //Member 객체 생성 후 저장
        Member member = requestDto.toEntity(encodePassword(requestDto.password()));
        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }


    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
