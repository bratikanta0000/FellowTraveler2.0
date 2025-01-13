document.getElementById("login-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    const response = await fetch("/api/users/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, passwordHash: password }),
    });

    if (response.ok) {
        const user = await response.json();
        alert(`Welcome back, ${user.name}!`);
        localStorage.setItem("currentUser", JSON.stringify(user));
        window.location.href = "/home";
    } else {
        alert("Invalid email or password!");
    }
});

document.getElementById("register-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    const name = document.getElementById("register-name").value;
    const email = document.getElementById("register-email").value;
    const password = document.getElementById("register-password").value;
    const phone = document.getElementById("register-phone").value;
    try {
        const response = await fetch("/api/users/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                name: name,
                email: email,
                passwordHash: password, // Password sent directly; consider hashing client-side for additional security
                phone: phone, // Default phone for simplicity; consider adding a phone input field
                preferences: JSON.stringify({ theme: "light", notifications: true }),
            }),
        });

        if (response.ok) {
            const user = await response.json();
            alert(`User ${user.name} registered successfully!`);
        } else {
            alert("Registration failed!");
        }
    } catch (error) {
        console.error("Error during registration:", error);
        alert("An error occurred during registration.");
    }

    const response = await fetch("/api/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, passwordHash: password }),
    });
});
