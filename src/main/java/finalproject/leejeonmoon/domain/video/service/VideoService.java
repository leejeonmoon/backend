package finalproject.leejeonmoon.domain.video.service;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class VideoService {

    private final VideoCapture camera;

    public VideoService() {
        camera = new VideoCapture(0); // 기본 웹캠을 의미
        if (!camera.isOpened()) {
            throw new RuntimeException("웹캠을 열 수 없습니다.");
        }
    }

    public InputStreamResource getVideoFrame() {
        Mat frame = new Mat();
        if (camera.read(frame)) {
            MatOfByte matOfByte = new MatOfByte();
            Imgcodecs.imencode(".jpg", frame, matOfByte, new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, 30)); // 품질을 50으로 설정

            byte[] frameBytes = matOfByte.toArray();
            InputStream inputStream = new ByteArrayInputStream(frameBytes);

            return new InputStreamResource(inputStream);
        } else {
            throw new RuntimeException("프레임을 읽을 수 없습니다.");
        }
    }
}
