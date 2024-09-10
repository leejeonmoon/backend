package finalproject.leejeonmoon.global.websocket;

import finalproject.leejeonmoon.domain.video.service.VideoService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Timer;
import java.util.TimerTask;

public class VideoWebSocketHandler extends TextWebSocketHandler {

    private final VideoService videoService;
    private Timer timer;
    private WebSocketSession currentSession;

    public VideoWebSocketHandler(VideoService videoService) {
        this.videoService = videoService;
        this.timer = new Timer(true);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        this.currentSession = session;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentSession != null && currentSession.isOpen()) {
                    try {
                        InputStreamResource videoStream = videoService.getVideoFrame();
                        InputStream inputStream = videoStream.getInputStream();
                        byte[] buffer = new byte[8192];
                        int bytesRead = inputStream.read(buffer);
                        if (bytesRead > 0) {
                            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, bytesRead);
                            currentSession.sendMessage(new BinaryMessage(byteBuffer));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        cancel(); // 타이머 작업 중지
                        closeSession();
                    }
                }
            }
        }, 0, 1000 / 15); // 30 FPS
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        closeSession();
    }

    private void closeSession() {
        if (currentSession != null && currentSession.isOpen()) {
            try {
                currentSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        timer.cancel();
    }
}
