package finalproject.leejeonmoon.domain.entity;

import finalproject.leejeonmoon.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Builder
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
    @Column(name = "alarm_type")
    private AlarmType type;

    @Column(name = "is_read")
    @ColumnDefault("false")
    @Builder.Default
    private Boolean isRead = false;

    private String title;
    private String content;
    private LocalDateTime createdDate;

    public void readAlarm() {
        this.isRead = true;
    }

    @Builder
    public Alarm(Member member, AlarmType type,String title, String content, LocalDateTime createdDate, Boolean isRead) {
        this.member = member;
        this.type = type;
        this.title = title;
        this.content = content;
        this.isRead = isRead;
        this.createdDate = createdDate;
    }
}
