package finalproject.leejeonmoon.domain.member.entity;

import finalproject.leejeonmoon.global.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long memberId;

    @Column(name = "email", length = 50, unique = true)
    @NotNull
    @Email
    @NotBlank
    private String email;

//    @Column(name = "password")
//    @NotNull
//    @NotBlank
//    private String password;


    @Column(name = "nickname", unique = true) // 10장 OAuth2: 관련 키 저장
    private String nickname;

    @Column(name = "provider")  // OAuth2 사용자인지 구분하기 위한 필드
    private String provider;

    @Builder
    public Member(String email, String nickname, String provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }

    @Override //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    // 10장: 사용자 이름 변경
    public Member update(String nickname){
        this.nickname = nickname;
        return this;
    }

    // 사용자의 id를 반환
    @Override
    public String getUsername() {
        return email;
    }
    // 사용자의 패스워드 반환
    @Override
    public String getPassword() {
        return null;
    }
    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true; // true -> 만료되지 않음
    }
    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 패스워드와 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
