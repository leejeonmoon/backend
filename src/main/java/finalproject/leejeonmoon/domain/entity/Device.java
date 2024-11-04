package finalproject.leejeonmoon.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id", updatable = false)
    private Long deviceId;

    @Column(nullable = false)
    private String ddnsUrl;

    @Column(nullable = false)
    private String macAddress;

    private LocalDateTime registeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Device(String ddnsUrl, String macAddress, Member member) {
        this.ddnsUrl = ddnsUrl;
        this.macAddress = macAddress;
        this.member = member;
        this.registeredAt = LocalDateTime.now();
    }
}
