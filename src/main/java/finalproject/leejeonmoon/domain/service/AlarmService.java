package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.dto.*;
import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.AlarmType;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AlarmService {
    private final MemberService memberService;
    private final AlarmRepository alarmRepository;
    private final EmitterService emitterService;

    // 알림 목록 조회
    @Transactional(readOnly = true)
    public List<AlarmResponseDto> getAlarmList() {
        Member member = memberService.getCurrentMember();
        List<Alarm> alarmsList = alarmRepository.findByMember(member);
        return alarmsList.stream()
                .map(AlarmResponseDto::from)
                .collect(Collectors.toList());
    }

    // 테스트 알림
    @Transactional
    public void testAlarm(){
        Member member = memberService.getCurrentMember();
        String content = MessageFormat.format(AlarmType.TEST.getMessagePattern(), "테스트 데이터");
        Alarm alarm = Alarm.builder()
                .member(member)
                .type(AlarmType.TEST)
                .title("테스트 알림")
                .content(content)
                .isRead(false)
                .build();
        alarmRepository.save(alarm);
        emitterService.notify(member.getMemberId(), AlarmNewDataDto.from(true), "new alarm");
    }

    // 전체 알림을 조회하고 읽은 상태로 변경하는 메서드
    public List<SseUnitDto> getAndReadAlarms() {
        List<SseUnitDto> alarmList = new ArrayList<>();
        Member currentMember = memberService.getCurrentMember();
        // 전체 알림을 받아옴
        List<Alarm> alarms = alarmRepository.findByMemberOrderByCreatedTimeDesc(currentMember);
        if (alarms != null && !alarms.isEmpty()) {
            for (Alarm alarm : alarms) {
                // 알림 유형에 따라 메시지를 생성
                String content = switch (alarm.getType()) {
                    case SNORT -> null;
                    case FIREWALL -> null;
                    case INTERFACE -> null;
                    case TEST -> MessageFormat.format(AlarmType.TEST.getMessagePattern(), "테스트 사용자");
                    // 추후 다른 알림 유형 추가
                };
                // SseUnitDto로 변환 후 리스트에 추가
                alarmList.add(SseUnitDto.from(alarm, content));
                // 알림을 읽은 상태로 변경
                alarm.readAlarm();
            }
            // 알림 상태를 저장
            alarmRepository.saveAll(alarms);
        }
        return alarmList;
    }
}
