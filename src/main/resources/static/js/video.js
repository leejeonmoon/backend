document.addEventListener("DOMContentLoaded", function () {
    const today = new Date();
    const videoList = document.getElementById("video-list");
    const videoModal = document.getElementById("videoModal");
    const modalVideo = document.getElementById("modalVideo");
    const closeModal = document.querySelector(".modal .close");
    const currentDateElement = document.getElementById("current-date");
    const datePicker = document.getElementById("date-picker");
    const selectDateBtn = document.getElementById("select-date-btn");

    const videosPerInterval = 6;
    const videos = generateFakeVideos(); // 임시로 생성된 비디오 데이터


    if (currentDateElement) {
        currentDateElement.textContent = today.toISOString().split('T')[0];
    }


    flatpickr(datePicker, {
        defaultDate: today,
        dateFormat: "Y-m-d",
        maxDate: today,
        appendTo: selectDateBtn.parentNode,
        onChange: function(selectedDates, dateStr) {
            const selectedDate = new Date(dateStr);
            if (selectedDate > today) {
                displayNoVideoMessage();
            } else {
                selectDateBtn.textContent = dateStr;
                loadVideosForDate(dateStr);
            }
        }
    });



    selectDateBtn.addEventListener("click", function () {
        datePicker._flatpickr.open();
    });


    function loadVideosForDate(date) {
        videoList.innerHTML = "";
        const filteredVideos = videos.filter(video => video.date === date);
        if (filteredVideos.length === 0) {
            displayNoVideoMessage();
        } else {
            renderVideos(filteredVideos.slice(0, videosPerInterval));
        }
    }


    function renderVideos(videosToShow) {
        videoList.innerHTML = '';
        videosToShow.forEach((video, index) => {
            const videoThumbnail = document.createElement("div");
            videoThumbnail.classList.add("video-thumbnail");
            videoThumbnail.setAttribute("data-video-url", video.url);
            videoThumbnail.innerHTML = `<img src="${video.thumbnail}" alt="video thumbnail"><p>Event ${index + 1}</p>`; // 이벤트 번호 1부터 시작
            videoThumbnail.addEventListener("click", function () {
                modalVideo.querySelector("source").src = video.url;
                modalVideo.load();
                videoModal.style.display = "flex";
            });
            videoList.appendChild(videoThumbnail);
        });
    }


    closeModal.addEventListener("click", function () {
        videoModal.style.display = "none";
        modalVideo.pause();
    });

    window.addEventListener("click", function (event) {
        if (event.target == videoModal) {
            videoModal.style.display = "none";
            modalVideo.pause();
        }
    });


    function displayNoVideoMessage() {
        videoList.innerHTML = "";
        const noVideoMessage = document.createElement("div");
        noVideoMessage.classList.add("no-video-message");
        noVideoMessage.textContent = "No Video";
        videoList.appendChild(noVideoMessage);
    }


    function generateFakeVideos() {
        const fakeVideos = [];
        for (let dayOffset = 0; dayOffset < 14; dayOffset++) {
            const date = new Date(today);
            date.setDate(today.getDate() - dayOffset);
            for (let i = 1; i <= 6; i++) {
                fakeVideos.push({
                    id: i,
                    url: `video${i + dayOffset * 6}.mp4`,
                    thumbnail: `thumbnail${i + dayOffset * 6}.jpg`,
                    title: `Event ${i}`,
                    date: date.toISOString().split('T')[0]
                });
            }
        }
        return fakeVideos;
    }


    loadVideosForDate(today.toISOString().split('T')[0]);
});