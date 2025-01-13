const currentUserRaw = localStorage.getItem("currentUser");

if (!currentUserRaw) {
    window.location.href = "/login";
}

async function fetchUserDetails() {
    try {
        const currentUser = JSON.parse(currentUserRaw);
        const response = await fetch(`/api/users/${currentUser.userId}`);
        if (!response.ok) {
            throw new Error("Failed to fetch user details");
        }
        const user = await response.json();
        document.getElementById("name").value = user.name;
        document.getElementById("email").value = user.email;
        document.getElementById("phone").value = user.phone || '';
        document.getElementById("preferences").value = user.preferences || '';
        if (user.profilePicture) {
            document.getElementById("profileImage").src = `data:image/jpeg;base64,${user.profilePicture}`;
        }
    } catch (error) {
        document.getElementById("errorMessage").innerText = error.message;
    }
}

async function updateUserSettings() {
    const formData = new FormData();
    formData.append("user", JSON.stringify({
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        preferences: document.getElementById("preferences").value
    }));

    const profilePictureFile = document.getElementById("profilePicture").files[0];
    if (profilePictureFile) {
        formData.append("profilePicture", profilePictureFile);
    }

    try {
        const currentUser = JSON.parse(currentUserRaw);
        const response = await fetch(`/api/users/${currentUser.userId}`, {
            method: "PUT",
            body: formData
        });

        if (!response.ok) {
            throw new Error("Failed to update user settings");
        }

        alert("User settings updated successfully!");
        fetchUserDetails(); // Refresh details
        window.location.href = "/home";
    } catch (error) {
        document.getElementById("errorMessage").innerText = error.message;
    }
}
// Fetch user details on page load
fetchUserDetails();