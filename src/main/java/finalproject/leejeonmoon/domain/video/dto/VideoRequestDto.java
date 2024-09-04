package finalproject.leejeonmoon.domain.video.dto;

import finalproject.leejeonmoon.domain.video.entity.Video;

public record VideoRequestDto(
    String title,
    byte[] frameData,
    long timestamp
) {
}
