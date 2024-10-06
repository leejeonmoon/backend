package finalproject.leejeonmoon.domain.entity;

import finalproject.leejeonmoon.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id", updatable = false)
    private Long alarmId;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private AlarmType type;

    private String title;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    public Alarm(AlarmType type,String title, String content, LocalDateTime createdDate) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
