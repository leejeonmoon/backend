package finalproject.leejeonmoon.global.config;

import finalproject.leejeonmoon.domain.video.service.VideoService;
import finalproject.leejeonmoon.global.websocket.VideoWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final VideoService videoService;

    public WebSocketConfig(VideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new VideoWebSocketHandler(videoService), "/ws/video")
                .setAllowedOrigins("*"); // CORS 설정
    }
}
