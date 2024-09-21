package finalproject.leejeonmoon.global.config.oauth;

import finalproject.leejeonmoon.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import finalproject.leejeonmoon.domain.member.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuth2UserCustomService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    // 구글로부터 받은 userRequest 데이터를 후처리하는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    // 유저가 있으면 업데이트, 없으면 유저 생성
    private Member saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        Member member = memberRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(Member.builder()
                        .email(email)
                        .nickname(name)
                        .provider("google")
                        .build());
        return memberRepository.save(member);
    }



}
