package finalproject.leejeonmoon.domain.service;

import finalproject.leejeonmoon.domain.dto.AlarmResponseDto;
import finalproject.leejeonmoon.domain.entity.Alarm;
import finalproject.leejeonmoon.domain.entity.Member;
import finalproject.leejeonmoon.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {
    private final MemberService memberService;
    private final AlarmRepository alarmRepository;

    // 알림 목록 조회
    @Transactional(readOnly = true)
    public List<AlarmResponseDto> getAlarmList() {
        Member member = memberService.getCurrentMember();
        List<Alarm> alarmsList = alarmRepository.findByMember(member);
        return alarmsList.stream()
                .map(AlarmResponseDto::from)
                .collect(Collectors.toList());
    }

}
