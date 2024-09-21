document.addEventListener("DOMContentLoaded", () => {
    // 현재 페이지의 URL을 가져와서 요청을 보냅니다.
    const currentUrl = window.location.pathname; // 현재 페이지의 경로 가져오기
    httpRequest('GET', currentUrl, null, success, fail); // 토큰이 없으면 로그인 페이지로 이동

    function success() {
        console.log("인증된 요청 성공");
    }

    function fail() {
        console.log("인증된 요청 실패");
        location.replace("/login");
    }
});


// HTTP 요청을 보내는 함수
function httpRequest(method, url, body, success, fail) {
    const accessToken = localStorage.getItem('access_token'); // localStorage에서 토큰 가져오기

    fetch(url, {
        method: method,
        headers: {
            'Authorization': 'Bearer ' + accessToken, // Authorization 헤더에 토큰 추가
            'Content-Type': 'application/json',
        },
        body: body,
    }).then(response => {
        if (response.status === 200 || response.status === 201) {
            return success();
        } else if (response.status === 401) {
            console.log('Unauthorized: ' + response.statusText);
            return fail();
        } else {
            return fail();
        }
    }).catch(error => {
        console.log('Request failed: ', error);
        return fail();
    });
}


