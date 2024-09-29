const token = searchParam('token');

if (token) {
    localStorage.setItem("access_token", token);
    console.log("토큰이 localStorage에 저장되었습니다.");
} else {
    console.log("토큰이 URL에 없습니다.");
}

function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}
