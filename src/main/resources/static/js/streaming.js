// 페이지 로드 후 DOM이 준비되면 실행
document.addEventListener("DOMContentLoaded", () => {
    // 재생 버튼 기능
    document.getElementById('play').addEventListener('click', function() {
        fetch('/camera/play', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });

    // 멈춤 버튼 기능
    document.getElementById('pause').addEventListener('click', function() {
        fetch('/camera/pause', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });

    // 카메라 회전 버튼 기능
    document.getElementById('rotate-left').addEventListener('click', function() {
        fetch('/camera/rotate/left', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('rotate-right').addEventListener('click', function() {
        fetch('/camera/rotate/right', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('rotate-up').addEventListener('click', function() {
        fetch('/camera/rotate/up', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });

    document.getElementById('rotate-down').addEventListener('click', function() {
        fetch('/camera/rotate/down', { method: 'POST' })
            .then(response => response.text())
            .then(data => console.log(data))
            .catch(error => console.error('Error:', error));
    });
});
