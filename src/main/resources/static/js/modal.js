// Get the modal element
var modal = document.getElementById('loginModal');

// Function to open the modal
function openModal() {
  if (modal) {
    modal.style.display = 'block';
  }
}

// Function to close the modal
function closeModal() {
  if (modal) {
    modal.style.display = 'none';
  }
}

// When the user clicks the "Login/Sign Up" button, open the modal
var btn = document.getElementById('loginSignupBtn');
if (btn) {
  btn.onclick = openModal;
}

// When the user clicks on the close button (×), close the modal
var closeBtn = document.querySelector('#loginModal .close');
if (closeBtn) {
  closeBtn.onclick = closeModal;
}

// When the user clicks anywhere outside of the modal content, close the modal
window.onclick = function(event) {
  if (event.target == modal) {
    closeModal();
  }
};

// Notification toggling function
function toggleNotification() {
  var notificationList = document.querySelector('.notification-list');
  if (notificationList) {
    notificationList.classList.toggle('show');
  }
}

// Function to add a notification
function addNotification(message) {
  var alertList = document.getElementById('notification-list');
  var emptyNotification = document.querySelector('.empty-notification');

  // Hide the "no new notifications" message when a new notification is added
  if (emptyNotification) {
    emptyNotification.style.display = 'none';
  }

  var newAlert = document.createElement('div');
  newAlert.classList.add('notification-item');
  newAlert.innerHTML = `<p>${message}</p>`;
  if (alertList) {
    alertList.insertBefore(newAlert, alertList.firstChild);
  }
}

// Initial test notifications
addNotification('이것은 테스트 알림입니다.');
addNotification('실시간 알림을 여기에 추가할 수 있습니다.');

// Close notification list when clicking outside of it
window.addEventListener('click', function(event) {
  var notificationList = document.querySelector('.notification-list');
  if (notificationList && !notificationList.contains(event.target) && !event.target.matches('.bell-icon')) {
    notificationList.classList.remove('show');
  }
});
