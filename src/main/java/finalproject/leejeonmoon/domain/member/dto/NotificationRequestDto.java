package finalproject.leejeonmoon.domain.member.dto;

import lombok.Builder;

public record NotificationRequestDto(
        String title,
        String message,
        String token
) {
    @Builder
    public NotificationRequestDto(String title, String message, String token) {
        this.title = title;
        this.message = message;
        this.token = token;
    }
}
