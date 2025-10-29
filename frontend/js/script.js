var apiUrl = 'http://localhost:8080/api';
var jwtToken = localStorage.getItem('jwtToken') || '';
var editingCarId = null;

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
                    <td class="border p-2">${car.licensePlate || ''}</td>
                    <td class="border p-2 space-x-2">
                        <button onclick="deleteCar(${car.id})" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">Delete</button>
                        <button onclick="editCar(${car.id})" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">Edit</button>
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
    .then(res => {
        if (!res.ok) throw new Error(`Delete failed: HTTP ${res.status}`);
        fetchCar();
    })
    .catch(error => console.error(error));
}

// ---------------------- EDIT / SAVE ----------------------
function editCar(carId) {
    fetch(`${apiUrl}/cars/${carId}`, { headers: { 'Authorization': `Bearer ${jwtToken}` }})
    .then(res => {
        if (!res.ok) throw new Error(`Fetch failed: HTTP ${res.status}`);
        return res.json();
    })
    .then(car => {
        editingCarId = carId;
        const modal = document.getElementById('carModal');
        modal.classList.remove('hidden');
        document.getElementById('modalTitle').innerText = "Edit Car";

        document.getElementById('carMake').value = car.make;
        document.getElementById('carModel').value = car.model;
        document.getElementById('carYear').value = car.year;
        document.getElementById('carColor').value = car.color;
        document.getElementById('carBodyType').value = car.bodyType;
        document.getElementById('carEngineType').value = car.engineType;
        document.getElementById('carLicensePlate').value = car.licensePlate || '';
    })
    .catch(error => console.error(error));
}

function saveCar(event) {
    event.preventDefault();
    const carData = {
        make: document.getElementById('carMake').value,
        model: document.getElementById('carModel').value,
        year: document.getElementById('carYear').value,
        color: document.getElementById('carColor').value,
        bodyType: document.getElementById('carBodyType').value,
        engineType: document.getElementById('carEngineType').value,
        licensePlate: document.getElementById('carLicensePlate').value
    };

    const url = editingCarId ? `${apiUrl}/cars/${editingCarId}` : `${apiUrl}/cars`;
    const method = editingCarId ? 'PUT' : 'POST';

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
        editingCarId = null;
        closeModal();
        fetchCar();
    })
    .catch(error => console.error(error));
}

// ---------------------- MODAL ----------------------
function openCreateModal() {
    editingCarId = null;
    const modal = document.getElementById('carModal');
    modal.classList.remove('hidden');
    document.getElementById('modalTitle').innerText = "Add New Car";
    document.getElementById('carForm').reset();
}

function closeModal() {
    document.getElementById('carModal').classList.add('hidden');
    document.getElementById('carForm').reset();
}

// ---------------------- AUTOLOAD ----------------------
window.addEventListener('DOMContentLoaded', () => {
    if (jwtToken) {
        document.getElementById('loginSection').classList.add('hidden');
        document.getElementById('carSection').classList.remove('hidden');
        fetchCar();
    }
});

window.editCar = editCar;
window.deleteCar = deleteCar;
window.logout = logout;
