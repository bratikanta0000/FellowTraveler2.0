document.getElementById("destinationForm").addEventListener("submit", async (event) => {
    event.preventDefault(); // Prevent form submission

    const formData = new FormData();

    // Create the destination object
    const destination = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        location: document.getElementById("location").value,
        category: document.getElementById("category").value,
    };

    // Append destination JSON as a string
    formData.append("destination", JSON.stringify(destination));

    // Append the cover photo file
    const coverPhotoFile = document.getElementById("coverPhoto").files[0];
    if (coverPhotoFile) {
        formData.append("coverPhoto", coverPhotoFile);
    }

    try {
        // Send POST request to create destination with cover photo
        const response = await fetch("/api/destinations", {
            method: "POST",
            body: formData,
        });

        if (response.ok) {
            const destinationResponse = await response.json();

            // Show success message
            document.getElementById("successMessage").innerText =
                `Destination "${destinationResponse.name}" has been added successfully!`;
            document.getElementById("destinationForm").reset();
        } else {
            throw new Error("Failed to create destination. Please try again.");
        }
    } catch (error) {
        alert(error.message);
    }
});
