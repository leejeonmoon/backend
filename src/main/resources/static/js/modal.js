// 모달 관련 요소 가져오기
var modal = document.getElementById("signupModal");
var btn = document.getElementById("signupBtn");
var span = document.getElementsByClassName("close")[0];

// 회원가입 버튼을 클릭하면 모달 열기
btn.onclick = function() {
  modal.style.display = "flex";  // 모달 창 열기
}

// 닫기(X) 버튼을 클릭하면 모달 닫기
span.onclick = function() {
  modal.style.display = "none";
}

// 모달 바깥 부분을 클릭하면 모달 닫기
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
