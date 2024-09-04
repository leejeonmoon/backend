package finalproject.leejeonmoon.domain.video.controller;

import finalproject.leejeonmoon.domain.video.dto.VideoRequestDto;
import finalproject.leejeonmoon.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    // 실시간 스트리밍
    @PostMapping("/stream")
    public ResponseEntity<?> streamVideo(@RequestBody VideoRequestDto requestDto) {
        InputStreamResource videoStream = videoService.startStream(requestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // 매 프레임이 JPEG 이미지임을 명시
        headers.add("Content-Disposition", "inline;filename=webcam-stream.jpg");

        return ResponseEntity.ok().headers(headers).body(videoStream);
    }
}
