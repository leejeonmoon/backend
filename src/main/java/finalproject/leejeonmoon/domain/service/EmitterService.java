package finalproject.leejeonmoon.domain.service;

import java.io.IOException;
import java.util.Optional;

import finalproject.leejeonmoon.domain.dto.AlarmNewDataDto;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.repository.AlarmRepository;
import finalproject.leejeonmoon.domain.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmitterService {
    private final EmitterRepository emitterRepository;
    private final MemberService memberService;

    private static final Long DEFAULT_TIMEOUT = 5L * 60 * 1000;  // 5분

    // 알림 구독
    public SseEmitter subscribe() {
        Member member = memberService.getCurrentMember();
        Long memberId = member.getMemberId();
        SseEmitter emitter = createEmitter(memberId);
//        // 이미 존재하는 Emitter가 있는지 체크
//        SseEmitter emitter = Optional.ofNullable(emitterRepository.get(memberId))
//                .orElseGet(() -> registerEmitter(memberId));
        sendToClient(memberId, "EventStream Created. [memberId="+ memberId + "]", "sse 접속 성공");
        return emitter;
    }

    public void notify(Long memberId, Object data, String comment) {
        sendToClient(memberId, data, comment);
    }

    private <T> void sendToClient(Long memberId, Object data, String comment) {
        SseEmitter emitter = emitterRepository.get(memberId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event()
                        .id(String.valueOf(memberId))
                        .name("sse")
                        .data(data)
                        .comment(comment));
            } catch (IOException e) {
                emitterRepository.delete(memberId);
                emitter.completeWithError(e);
            }
        }
    }

    private SseEmitter createEmitter(Long memberId) {
        SseEmitter emitter = new SseEmitter(DEFAULT_TIMEOUT);
        emitterRepository.save(memberId, emitter);
        emitter.onCompletion(() -> emitterRepository.delete(memberId));
        emitter.onTimeout(() -> emitterRepository.delete(memberId));

        return emitter;
    }

}
