package finalproject.leejeonmoon.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@RequiredArgsConstructor
public class EmitterRepository {
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();
    // 동시성 문제를 위해 ConcurrentHashMap<을 사용

    public void save(Long id, SseEmitter emitter) {
        emitters.put(id, emitter);
    }

    public void delete(Long memberId) {
        emitters.remove(memberId);
    }

    public SseEmitter get(Long memberId) {
        return emitters.get(memberId);
    }
}
