document.addEventListener("DOMContentLoaded", function() {
    // 페이지가 로드되면 2분마다 알람 업데이트
    setInterval(updateAlarms, 120000); // 2분(120000ms)

    // 초기 알람 업데이트
    updateAlarms();
});

// 알람 데이터를 수동으로 업데이트하는 함수
function manualUpdateAlarms() {
    updateAlarms();
}

// 라즈베리 파이에서 알람 데이터를 가져오는 함수
function updateAlarms() {
    const testAlarms = {
        alarms: [
            { message: "Security Alert: Possible Dictionary Attack" },
            { message: "Suspicious Access Attempt Detected" },
            { message: "Excessive Login Failures Detected" },
            {message:"Suspicious HTTP POST Request Detected"},
            {message: "Suspicious Authentication Pattern"},
            {message: "SSH Brute Force Attempt Detected"},
            {message: "Malicious SQL Injection"},
            { message: "Possible Port Scan" },
            { message: "Suspicious Access Attempt Detected" },
            { message: "Login Attempts: 10-30 times detected" },
            {message:"Suspicious HTTP POST Request Detected"},
            {message: "ICMP Ping Flood Detected"},
            {message: "SSH Brute Force Attempt Detected"},
            {message: "Malicious SQL Injection"},
            { message: "Security Alert: Possible Dictionary Attack" },
            { message: "Suspicious Access Attempt Detected" },
            { message: "Login Attempts: 10-30 times detected" },
            {message:"Suspicious HTTP POST Request Detected"},
            {message: "ICMP Ping Flood Detected"},
            {message: "SSH Brute Force Attempt Detected"},
            {message: "Malicious SQL Injection"}
        ]
    };

    const alarmContainer = document.getElementById('alarm-container');
    alarmContainer.innerHTML = '';

    if (testAlarms.alarms.length === 0) {
        alarmContainer.innerHTML = '<p>현재 알람이 없습니다.</p>';
    } else {
        testAlarms.alarms.forEach(alarm => {
            const alarmItem = document.createElement('div');
            alarmItem.classList.add('alarm-item');

            // 알람 아이콘 추가
            const icon = document.createElement('img');
            icon.src = '/img/notification-bell.svg'; // 알람 아이콘 이미지 경로
            icon.alt = 'Alarm Icon';
            icon.classList.add('alarm-icon'); // 스타일 적용을 위한 클래스 추가

            // 메시지 텍스트 추가
            const message = document.createElement('span');
            message.textContent = alarm.message;

            // 아이콘과 메시지를 alarmItem에 추가
            alarmItem.appendChild(icon);
            alarmItem.appendChild(message);

            alarmContainer.appendChild(alarmItem);
        });
    }
}