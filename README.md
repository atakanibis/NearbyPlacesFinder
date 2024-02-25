# Nearby Places Finder

This application is a Full-Stack web application that allows users to find nearby places within a specified location and radius.

## Features

- Users can search for nearby places by entering a specific longitude, latitude, and radius value.
- Search results can be displayed on Google Maps.
- The application includes a backend server with Java and Spring Boot and a frontend server with Vue.js.
- The backend processes requests via RESTful APIs and searches for nearby places using the Google Places API.
- Search results are stored in the database and retrieved from the database when the same request is made again.

## Technologies Used

- Java 17
- Spring Boot 2.6.3
- Vue.js
- MySQL
- Maven

## Getting Started

1. Clone the project:

    ```
    git clone https://github.com/atakanibis/nearby-places-finder.git
    ```

2. Navigate to the project directory:

    ```
    cd nearby-places-finder
    ```

3. Run the project:

    ```
    mvn spring-boot:run
    ```

4. Visit `http://localhost:8070` in your browser to view the application.
