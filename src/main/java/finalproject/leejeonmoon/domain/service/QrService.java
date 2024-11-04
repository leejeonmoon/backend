package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class QrService {
    private final MemberService memberService;

    public String getQrUrl(){
        Member member = memberService.getCurrentMember();
        Long memberId = member.getMemberId();

        String url = "http://localhost:8080/qr/{memberId}";
        return url.replace("{memberId}", memberId.toString());
    }

    public void updateMemberQrCode(String qrCodeUrl) {
        Member member = memberService.getCurrentMember();
        member.updateQr(qrCodeUrl);
    }
}
