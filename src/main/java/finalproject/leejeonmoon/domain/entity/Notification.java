package finalproject.leejeonmoon.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String token;

    @Builder
    public Notification(String token) {
        this.token = token;
    }

    public void updateToken(String newToken) {
        this.token = newToken;
    }

    public void confirmUser(Member member) {
        this.member = member;
    }
}
