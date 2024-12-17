function toggleDetails(id) {
    const details = document.getElementById(id);
    const allDetails = document.querySelectorAll('.risk-details');

    allDetails.forEach(detail => {
        if (detail.id !== id) {
            detail.style.display = 'none';
        }
    });

    details.style.display = details.style.display === 'block' ? 'none' : 'block';
}