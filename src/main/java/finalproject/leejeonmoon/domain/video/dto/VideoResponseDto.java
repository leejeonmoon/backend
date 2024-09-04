package finalproject.leejeonmoon.domain.video.dto;

public record VideoResponseDto(
        Long videoId,
        String title,
        byte[] frameData,
        long timestamp
) {
}
