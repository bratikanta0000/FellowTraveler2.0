// Handle range input value updates and submission
document.addEventListener('DOMContentLoaded', () => {
    const question1 = document.getElementById('question1');
    const question2 = document.getElementById('question2');
    const question3 = document.getElementById('question3');
    const question4 = document.getElementById('question4');

    const question1Value = document.getElementById('question1Value');
    const question2Value = document.getElementById('question2Value');
    const question3Value = document.getElementById('question3Value');
    const question4Value = document.getElementById('question4Value');

    // Update the displayed value when the range input changes
    question1.addEventListener('input', () => {
        question1Value.textContent = question1.value;
    });
    question2.addEventListener('input', () => {
        question2Value.textContent = question2.value;
    });
    question3.addEventListener('input', () => {
        question3Value.textContent = question3.value;
    });
    question4.addEventListener('input', () => {
        question4Value.textContent = question4.value;
    });

    // Handle the review form submission
    const form = document.getElementById('reviewForm');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const userId = JSON.parse(localStorage.getItem('currentUser')).userId; // Assuming user is logged in
        const destinationId = window.location.pathname.split('/')[2]; // Get destinationId from URL

        const review = {
            userId: { userId },
            destinationId: { destinationId },
            rating: calculateAverageRating(),
            comment: document.getElementById('comment').value,
        };

        try {
            const response = await fetch('/api/reviews/submitreview', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(review),
            });

            if (!response.ok) {
                throw new Error('Failed to submit review');
            }
            else{
                alert('Your review has been submitted successfully!');
                window.location.href = `/destinations/${destinationId}`; // Redirect back to destination page
            }
        } catch (error) {
            console.error(error.message);
            alert('Failed to submit review. Please try again later.');
        }
    });

    // Calculate average rating from the Likert scale questions
    function calculateAverageRating() {
        const ratings = [
            parseInt(question1.value),
            parseInt(question2.value),
            parseInt(question3.value),
            parseInt(question4.value),
        ];

        const total = ratings.reduce((sum, rating) => sum + rating, 0);
        return total / ratings.length;
    }
});
