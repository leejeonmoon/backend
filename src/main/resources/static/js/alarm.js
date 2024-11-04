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
    // 여기서는 예시로 라즈베리 파이에서 데이터를 가져온다고 가정합니다.
    // 실제 구현 시에는 AJAX나 Fetch API를 통해 서버와 통신하여 알람 데이터를 가져와야 합니다.
    fetch('/alarms')
        .then(response => response.json())
        .then(data => {
            const alarmContainer = document.getElementById('alarm-container');
            alarmContainer.innerHTML = '';

            if (data.alarms.length === 0) {
                alarmContainer.innerHTML = '<p>현재 알람이 없습니다.</p>';
            } else {
                data.alarms.forEach(alarm => {
                    const alarmItem = document.createElement('div');
                    alarmItem.classList.add('alarm-item');
                    alarmItem.textContent = alarm.message;
                    alarmContainer.appendChild(alarmItem);
                });
            }
        })
        .catch(error => {
            console.error('알람 데이터를 불러오는 중 오류 발생:', error);
            document.getElementById('alarm-container').innerHTML = '<p>알람 데이터를 불러오는 중 오류가 발생했습니다.</p>';
        });
}