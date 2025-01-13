const destinationId = window.location.pathname.split('/').pop();

// Function to fetch destination details
async function fetchDestinationDetails() {
    try {
        const response = await fetch(`/api/destinations/${destinationId}`);
        if (!response.ok) {
            throw new Error('Failed to fetch destination details');
        }

        const destination = await response.json();

        // Populate destination details
        document.getElementById('destinationName').innerText = destination.name;
        document.getElementById('destinationImage').src = destination.coverPhoto
            ? `data:image/jpeg;base64,${destination.coverPhoto}`
            : 'https://via.placeholder.com/800x400?text=No+Image';
        document.getElementById('destinationLocation').innerText = `Location: ${destination.location}`;
        document.getElementById('destinationCategory').innerText = `Category: ${destination.category}`;
        document.getElementById('destinationDescription').innerText = destination.description || 'No description available.';
        document.getElementById('destinationRating').innerText = destination.rating
            ? `Rating: ⭐ ${destination.rating.toFixed(1)}`
            : 'Rating: No reviews yet';

        // Add "Book Now" functionality
        setupBookNowButton(destinationId);

        // Fetch and display reviews
        await fetchReviews();

        // Add event listener for the "Give a Review" button
        setupGiveReviewButton(destinationId);
    } catch (error) {
        console.error(error.message);
        alert('Failed to load destination details. Please try again later.');
    }
}

// Function to fetch reviews for the destination
async function fetchReviews() {
    try {
        const response = await fetch(`/api/destinations/${destinationId}/reviews`);
        if (!response.ok) {
            throw new Error('Failed to fetch reviews');
        }

        const reviews = await response.json();
        const reviewsContainer = document.getElementById('reviewsContainer');
        reviewsContainer.innerHTML = ''; // Clear existing content

        if (reviews.length === 0) {
            reviewsContainer.innerText = 'No reviews available.';
            return;
        }

        reviews.forEach(review => {
            const reviewDiv = document.createElement('div');
            reviewDiv.classList.add('review');

            const reviewer = document.createElement('p');
            reviewer.innerText = `Reviewer: ${review.user.name}`;
            reviewDiv.appendChild(reviewer);

            const comment = document.createElement('p');
            comment.innerText = `Comment: ${review.comment}`;
            reviewDiv.appendChild(comment);

            const rating = document.createElement('p');
            rating.innerText = `Rating: ⭐ ${review.rating}`;
            reviewDiv.appendChild(rating);

            reviewsContainer.appendChild(reviewDiv);
        });
    } catch (error) {
        console.error(error.message);
        alert('Failed to load reviews. Please try again later.');
    }
}

// Function to set up the "Book Now" button
function setupBookNowButton(destinationId) {
    const bookNowButton = document.getElementById('bookNowButton');
    bookNowButton.addEventListener('click', () => {
        // Redirect to the booking page
        window.location.href = `/booking/${destinationId}`;
    });
}

// Function to set up the "Give a Review" button
function setupGiveReviewButton(destinationId) {
    const giveReviewButton = document.getElementById('giveReviewButton');
    giveReviewButton.addEventListener('click', () => {
        // Redirect to the review form page
        window.location.href = `/destinations/${destinationId}/review`;
    });
}

// Fetch destination details on page load
fetchDestinationDetails();
