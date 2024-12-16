document.addEventListener("DOMContentLoaded", () => {
    // Initialize Flatpickr
    const datePicker = flatpickr("#date-picker", {
        dateFormat: "Y-m-d",
        defaultDate: "today",
        maxDate: "today",
        onChange: function(selectedDates, dateStr) {
            fetchRecordedVideos(dateStr);
        },
        onOpen: function() {
            document.getElementById('date-picker').style.display = 'block';
        },
        onClose: function() {
            document.getElementById('date-picker').style.display = 'none';
        }
    });

    // Select Date 버튼 클릭 이벤트
    document.getElementById('select-date-btn').addEventListener('click', function() {
        datePicker.toggle();
    });

    // Function to fetch videos
    function fetchRecordedVideos(date) {
        const videoList = document.getElementById('video-list');
        videoList.innerHTML = '<div class="error-message">Loading videos...</div>';

        fetch(`/videos?date=${date}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(videos => {
                videoList.innerHTML = '';

                if (!videos || videos.length === 0) {
                    videoList.innerHTML = '<div class="error-message">No videos found for the selected date.</div>';
                    return;
                }

                videos.forEach(video => {
                    const videoItem = document.createElement('div');
                    videoItem.className = 'video-thumbnail';
                    videoItem.innerHTML = `
                        <p>${video.name}</p>
                        <a href="http://3.38.149.191/videos/${video.name}" target="_blank">Watch Video</a>
                    `;
                    videoList.appendChild(videoItem);
                });
            })
            .catch(error => {
                console.error('Error:', error);
                videoList.innerHTML = '<div class="error-message">Error loading videos. Please try again later.</div>';
            });
    }

    // Load initial videos
    const today = new Date().toISOString().split('T')[0];
    fetchRecordedVideos(today);
});