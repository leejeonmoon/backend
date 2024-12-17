// Constants
const API_BASE_URL = 'http://3.38.149.191';
const ELEMENTS = {
    datePicker: '#date-picker',
    selectDateBtn: '#select-date-btn',
    videoList: '#video-list'
};

// Flatpickr Configuration
const FLATPICKR_CONFIG = {
    dateFormat: "Y-m-d",
    defaultDate: "today",
    maxDate: "today",
    onChange: (selectedDates, dateStr) => fetchRecordedVideos(dateStr),
    onOpen: () => toggleDatePicker(true),
    onClose: () => toggleDatePicker(false)
};

// Helper Functions
function toggleDatePicker(show) {
    document.querySelector(ELEMENTS.datePicker).style.display = show ? 'block' : 'none';
}

function createVideoElement(video) {
    const videoItem = document.createElement('div');
    videoItem.className = 'video-thumbnail';
    videoItem.innerHTML = `
        <p>${video.name}</p>
        <a href="${API_BASE_URL}/videos/${video.name}" target="_blank">Watch Video</a>
    `;
    return videoItem;
}

// Video Selection Functions
function selectVideo(container) {
    // Reset previously selected video
    document.querySelectorAll('.video-container').forEach(el => {
        el.classList.remove('selected');
        el.nextElementSibling.style.display = 'none';
    });
    
    // Select new video
    container.classList.add('selected');
    container.nextElementSibling.style.display = 'block';
}

function playVideo(event, cameraId) {
    event.stopPropagation();
    console.log(`Playing video from Camera ${cameraId}`);
    // TODO: Implement video playback functionality
    // window.location.href = `/video/play/${cameraId}`;
}

// API Functions
async function fetchRecordedVideos(date) {
    const videoList = document.querySelector(ELEMENTS.videoList);
    videoList.innerHTML = '<div class="error-message">Loading videos...</div>';

    try {
        const response = await fetch(`/videos?date=${date}`);
        if (!response.ok) throw new Error('Network response was not ok');

        const videos = await response.json();
        
        if (!videos || videos.length === 0) {
            videoList.innerHTML = '<div class="error-message">No videos found for the selected date.</div>';
            return;
        }

        videoList.innerHTML = '';
        videos.forEach(video => {
            videoList.appendChild(createVideoElement(video));
        });

    } catch (error) {
        console.error('Error:', error);
        videoList.innerHTML = '<div class="error-message">Error loading videos. Please try again later.</div>';
    }
}

// Initialize
document.addEventListener("DOMContentLoaded", () => {
    // Initialize Flatpickr
    const datePicker = flatpickr(ELEMENTS.datePicker, FLATPICKR_CONFIG);

    // Setup event listeners
    document.querySelector(ELEMENTS.selectDateBtn).addEventListener('click', () => {
        datePicker.toggle();
    });

    // Load initial videos
    const today = new Date().toISOString().split('T')[0];
    fetchRecordedVideos(today);
});