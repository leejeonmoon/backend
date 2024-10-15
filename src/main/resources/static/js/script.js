document.addEventListener("DOMContentLoaded", function() {
    const rotateLeft = document.getElementById("rotate-left");
    const rotateRight = document.getElementById("rotate-right");
    const rotateUp = document.getElementById("rotate-up");
    const rotateDown = document.getElementById("rotate-down");

    if (rotateLeft) {
        rotateLeft.addEventListener("click", function() {
            fetch("http://:8880/camera/rotate/left", { method: "POST" });
        });
    }

    if (rotateRight) {
        rotateRight.addEventListener("click", function() {
            fetch("http://<Raspberry Pi IP>:8880/camera/rotate/right", { method: "POST" });
        });
    }

    if (rotateUp) {
        rotateUp.addEventListener("click", function() {
            fetch("http://<Raspberry Pi IP>:8880/camera/rotate/up", { method: "POST" });
        });
    }

    if (rotateDown) {
        rotateDown.addEventListener("click", function() {
            fetch("http://<Raspberry Pi IP>:8880/camera/rotate/down", { method: "POST" });
        });
    }
});
