document.addEventListener("DOMContentLoaded", function () {
    const video = document.getElementById("stream");

    document.getElementById("play").addEventListener("click", () => video.play());
    document.getElementById("pause").addEventListener("click", () => video.pause());

    document.getElementById("rotate-left").addEventListener("click", () => alert("좌"));
    document.getElementById("rotate-right").addEventListener("click", () => alert("우"));
    document.getElementById("rotate-up").addEventListener("click", () => alert("위"));
    document.getElementById("rotate-down").addEventListener("click", () => alert("아래"));
});