package finalproject.leejeonmoon;

import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeejeonmoonApplication {

    public static void main(String[] args) {
        // OpenCV 네이티브 라이브러리 로드
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        SpringApplication.run(LeejeonmoonApplication.class, args);
    }

}
