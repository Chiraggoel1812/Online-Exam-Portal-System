const BASE_URL = "http://localhost:8080/api";

function saveCredentials(email, password, role, userId) {
    localStorage.setItem("email", email);
    localStorage.setItem("password", password);
    localStorage.setItem("role", role);
    localStorage.setItem("userId", userId);
}

function getAuthHeader() {
    const email = localStorage.getItem("email");
    const password = localStorage.getItem("password");
    if (!email || !password) return {};
    const encoded = btoa(email + ":" + password);
    return { "Authorization": "Basic " + encoded };
}

function isLoggedIn() {
    return localStorage.getItem("email") !== null;
}

function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}

async function apiGet(endpoint) {
    const response = await fetch(BASE_URL + endpoint, {
        method: "GET",
        headers: { ...getAuthHeader() }
    });
    if (!response.ok) throw new Error("Request failed: " + response.status);
    return await response.json();
}

async function apiPost(endpoint, data) {
    const response = await fetch(BASE_URL + endpoint, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeader()
        },
        body: JSON.stringify(data)
    });
    return response;
}
async function apiPut(endpoint, data) {
    const response = await fetch(BASE_URL + endpoint, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            ...getAuthHeader()
        },
        body: JSON.stringify(data)
    });
    return response;
}

async function apiDelete(endpoint) {
    const response = await fetch(BASE_URL + endpoint, {
        method: "DELETE",
        headers: { ...getAuthHeader() }
    });
    return response;
}