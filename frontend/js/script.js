var apiUrl = 'http://localhost:8080/api';
var jwtToken = localStorage.getItem('jwtToken') || '';

// ---------------------- LOGOUT ----------------------
function logout() {
    localStorage.removeItem('jwtToken');
    jwtToken = '';
    document.getElementById('carSection').classList.add('hidden');
    document.getElementById('carModal').classList.add('hidden');
    document.getElementById('loginSection').classList.remove('hidden');
    alert('Logged out successfully!');
}

// ---------------------- LOGIN ----------------------
document.getElementById('loginBtn').addEventListener('click', function(e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    login(username, password);
});

function login(username, password) {
    fetch(`${apiUrl}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    })
    .then(res => {
        if (!res.ok) throw new Error('Invalid credentials');
        return res.json();
    })
    .then(data => {
        jwtToken = data.token;
        localStorage.setItem('jwtToken', jwtToken);

        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('carSection').classList.remove('hidden');

        alert('Login successful!');
        fetchCar();
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Login failed');
    });
}

// ---------------------- FETCH CARS ----------------------
function fetchCar() {
    if (!jwtToken) return;

    fetch(`${apiUrl}/cars`, { headers: { 'Authorization': `Bearer ${jwtToken}` }})
    .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
    })
    .then(cars => {
        const row = document.getElementById('carTableBody');
        row.innerHTML = '';
        let counter = 0;
        cars.forEach(car => {
            row.innerHTML += `
            <tr class="text-center">
                <td class="border p-2">${++counter}</td>
                <td class="border p-2">${car.make}</td>
                <td class="border p-2">${car.model}</td>
                <td class="border p-2">${car.year}</td>
                <td class="border p-2">${car.color}</td>
                <td class="border p-2">${car.bodyType}</td>
                <td class="border p-2">${car.engineType}</td>
                <td class="border p-2">${car.licensePlate}</td>
                <td class="border p-2 space-x-2">
                    <button onclick="deleteCar(${car.id})" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">Delete</button>
                    <button onclick="openEditModal(${car.id}, '${car.make}', '${car.model}', ${car.year}, '${car.color}', '${car.bodyType}', '${car.engineType}', '${car.licensePlate}')" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">Edit</button>
                </td>
            </tr>`;
        });
    })
    .catch(error => console.error('Error fetching cars:', error));
}

// ---------------------- DELETE ----------------------
function deleteCar(carId) {
    fetch(`${apiUrl}/cars/${carId}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${jwtToken}` }
    })
    .then(() => fetchCar())
    .catch(error => console.error(error));
}

// ---------------------- MODAL ----------------------
function openCreateModal() {
    document.getElementById("carForm").reset();
    document.getElementById("carId").value = "";
    document.getElementById("modalTitle").innerText = "Add Car";
    document.getElementById("carModal").classList.remove("hidden");
}

function openEditModal(id, make, model, year, color, bodyType, engineType, licensePlate) {
    document.getElementById("carId").value = id;
    document.getElementById("carMake").value = make;
    document.getElementById("carModel").value = model;
    document.getElementById("carYear").value = year;
    document.getElementById("carColor").value = color;
    document.getElementById("carBodyType").value = bodyType;
    document.getElementById("carEngineType").value = engineType;
    document.getElementById("carLicensePlate").value = licensePlate;
    document.getElementById("modalTitle").innerText = "Edit Car";
    document.getElementById("carModal").classList.remove("hidden");
}

function closeModal() {
    document.getElementById("carModal").classList.add("hidden");
}

// ---------------------- SAVE CAR ----------------------
function saveCar(event) {
    event.preventDefault();
    const carId = document.getElementById("carId").value;
    const carData = {
        make: document.getElementById("carMake").value,
        model: document.getElementById("carModel").value,
        year: document.getElementById("carYear").value,
        color: document.getElementById("carColor").value,
        bodyType: document.getElementById("carBodyType").value,
        engineType: document.getElementById("carEngineType").value,
        licensePlate: document.getElementById("carLicensePlate").value
    };

    const url = carId ? `${apiUrl}/cars/${carId}` : `${apiUrl}/cars`;
    const method = carId ? 'PUT' : 'POST';

    fetch(url, {
        method,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwtToken}`
        },
        body: JSON.stringify(carData)
    })
    .then(res => {
        if (!res.ok) throw new Error('Save failed');
        return res.json();
    })
    .then(() => {
        closeModal();
        fetchCar();
    })
    .catch(error => console.error(error));
}

// ---------------------- AUTOLOAD ----------------------
window.addEventListener('DOMContentLoaded', () => {
    if (jwtToken) {
        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('carSection').classList.remove('hidden');
        fetchCar();
    }
});

window.deleteCar = deleteCar;
window.openEditModal = openEditModal;
window.openCreateModal = openCreateModal;
window.closeModal = closeModal;
window.saveCar = saveCar;
window.logout = logout;
