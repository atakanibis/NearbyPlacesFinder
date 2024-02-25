let map;
let markers = [];

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 0, lng: 0 },
        zoom: 8,
    });
}

document.getElementById("searchButton").addEventListener("click", searchPlaces);

function searchPlaces() {
    const longitude = parseFloat(document.getElementById("longitude").value);
    const latitude = parseFloat(document.getElementById("latitude").value);
    const radius = parseFloat(document.getElementById("radius").value);

    const location = { lat: latitude, lng: longitude };

    map.setCenter(location);
    map.setZoom(14);

    clearMarkers();

    // Add marker
    const marker = new google.maps.Marker({
        position: location,
        map: map,
        title: "Your Location",
    });
    markers.push(marker);

    fetch(`http://localhost:8070/api/places?longitude=${longitude}&latitude=${latitude}&radius=${radius}`)
        .then(response => response.json())
        .then(data => {
            const placesList = document.getElementById("placesList");
            placesList.innerHTML = "<h2>Nearby Places:</h2>";
            data.forEach(place => {
                const placeItem = document.createElement("div");
                placeItem.textContent = `Name: ${place.name}, Address: ${place.address}`;
                placesList.appendChild(placeItem);

                const placeLocation = { lat: place.latitude, lng: place.longitude };

                const placeMarker = new google.maps.Marker({
                    position: placeLocation,
                    map: map,
                    title: place.name,
                    icon: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
                });
                markers.push(placeMarker);

                placeMarker.addListener("click", () => {
                    showPlaceInfo(place);
                });
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function clearMarkers() {
    markers.forEach(marker => {
        marker.setMap(null);
    });
    markers = [];
}

function showPlaceInfo(place) {
    const placesInfo = document.getElementById("placesInfo");
    placesInfo.innerHTML = `
        <h2>Place Information:</h2>
        <p><strong>Name:</strong> ${place.name}</p>
        <p><strong>Address:</strong> ${place.address}</p>
        <p><strong>Latitude:</strong> ${place.latitude}</p>
        <p><strong>Longitude:</strong> ${place.longitude}</p>
        <p><strong>Rating:</strong> ${place.rating}</p>
        <p><strong>Phone Number:</strong> ${place.phoneNumber}</p>
    `;
}
