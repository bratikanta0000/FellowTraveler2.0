const destinationId = window.location.pathname.split('/').pop(); // Extract destinationId from URL
const userId = JSON.parse(localStorage.getItem('currentUser')).userId; // Assume currentUser is stored in localStorage

document.getElementById('bookingForm').addEventListener('submit', async (event) => {
    event.preventDefault(); // Prevent form from refreshing the page

    // Collect form values
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const totalBudget = document.getElementById('totalBudget').value;
    const serviceType = document.getElementById('serviceType').value;
    const serviceName = document.getElementById('serviceName').value;
    const bookingDate = document.getElementById('bookingDate').value;
    const cost = document.getElementById('cost').value;

    // Build the JSON request
    const bookingData = {
        itinerary: {
            userId: userId, // Nested User
            startDate: startDate,
            endDate: endDate,
            totalBudget: parseFloat(totalBudget),
        },
        destinationId: parseInt(destinationId),
        serviceType: serviceType,
        serviceName: serviceName,
        bookingDate: bookingDate,
        cost: parseFloat(cost),
    };

    try {
        // Send the booking request
        const response = await fetch('/api/bookings', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData),
        });

        if (!response.ok) {
            throw new Error('Failed to create booking');
        }

        const result = await response.json();
        alert('Booking created successfully!');
        window.location.href = `/home`; // Redirect to confirmation page
    } catch (error) {
        console.error(error.message);
        alert('Error creating booking. Please try again.');
    }
});
