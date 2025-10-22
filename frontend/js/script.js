const apiBase = "http://localhost:8080/api/cars"

document.addEventListener("DOMContentLoaded", fetchCars)

function fetchCars() {
    fetch(apiBase)
        .then(res => res.json())
        .then(cars => {
            const body = document.getElementById("carTableBody")
            body.innerHTML = ""
            var counter = 0;
            cars.forEach(car => {
                body.innerHTML += `
              <tr class="text-center">
                <td class="border p-2">${++counter}</td>
                <td class="border p-2">${car.make}</td>
                <td class="border p-2">${car.model}</td>
                <td class="border p-2">${car.year}</td>
                <td class="border p-2">${car.color}</td>
                <td class="border p-2">${car.bodyType}</td>
                <td class="border p-2">${car.engineType}</td>
                <td class="border p-2">${car.licensePlate}</td>
                <td class="border p-2">
                  <button onclick="openEditModal(${car.id}, '${car.make}', '${car.model}', ${car.year}, '${car.color}', '${car.bodyType}', '${car.engineType}', '${car.licensePlate}')" class="bg-yellow-500 text-white px-3 py-1 rounded hover:bg-yellow-600">Edit</button>
                  <button onclick="deleteCar(${car.id})" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">Delete</button>
                </td>
              </tr>`
            })
        })
        .catch(err => console.error(err))
}

function openCreateModal() {
    document.getElementById("carForm").reset()
    document.getElementById("carId").value = ""
    document.getElementById("modalTitle").innerText = "Add Car"
    document.getElementById("carModal").classList.remove("hidden")
}

function openEditModal(id, make, model, year, color) {
    document.getElementById("carId").value = id
    document.getElementById("carMake").value = make
    document.getElementById("carModel").value = model
    document.getElementById("carYear").value = year
    document.getElementById("carColor").value = color
    document.getElementById("carBodyType").value = bodyType
    document.getElementById("carEngineType").value = engineType
    document.getElementById("carLicensePlate").value = licensePlate
    document.getElementById("modalTitle").innerText = "Edit Car"
    document.getElementById("carModal").classList.remove("hidden")
}

function closeModal() {
    document.getElementById("carModal").classList.add("hidden")
}

function saveCar(e) {
    e.preventDefault()
    const id = document.getElementById("carId").value
    const make = document.getElementById("carMake").value
    const model = document.getElementById("carModel").value
    const year = document.getElementById("carYear").value
    const color = document.getElementById("carColor").value
    const bodyType = document.getElementById("carBodyType").value
    const engineType = document.getElementById("carEngineType").value
    const licensePlate = document.getElementById("carLicensePlate").value



    const car = { make, model, year, color, bodyType, engineType, licensePlate }
    const method = id ? "PUT" : "POST"
    const url = id ? `${apiBase}/${id}` : apiBase

    fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(car)
    })
        .then(res => res.json())
        .then(() => {
            closeModal()
            fetchCars()
        })
        .catch(err => console.error(err))
}

function deleteCar(id) {
    if (!confirm("Delete this car?")) return
    fetch(`${apiBase}/${id}`, { method: "DELETE" })
        .then(() => fetchCars())
        .catch(err => console.error(err))
}