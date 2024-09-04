package finalproject.leejeonmoon.domain.video.service;

import finalproject.leejeonmoon.domain.video.dto.VideoRequestDto;
import lombok.RequiredArgsConstructor;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class VideoService {

    public InputStreamResource startStream(VideoRequestDto requestDto) {
        VideoCapture camera = new VideoCapture(0); // 0번 장치는 기본 웹캠을 의미
        Mat frame = new Mat();
        if (!camera.isOpened()) {
            throw new RuntimeException("웹캠을 열 수 없습니다.");
        }

        // 프레임을 캡처
        if (camera.read(frame)) {
            // 프레임을 MatOfByte로 인코딩
            MatOfByte matOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", frame, matOfByte);

            byte[] frameBytes = matOfByte.toArray();

            // InputStream으로 변환
            InputStream inputStream = new ByteArrayInputStream(frameBytes);

            return new InputStreamResource(inputStream);
        } else {
            throw new RuntimeException("프레임을 읽을 수 없습니다.");
        }
    }
}
