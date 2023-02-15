# Pismo Code Assessment
This project contains the application code for the assessment's first phase. The complete project setup details are given as below:

## How to run?
1. Build a docker image using the Dockerfile in the project by using the following command inside the project directory:
    ```
    docker build -t pismo-assessment:latest .
    ```
2. Run the docker image and expose the port 8080
    ```
    docker run -p 8080:8080 --name container_1 pismo-assessment:latest
    ```
3. The application can be accessed by navigating to ```http://localhost:8080/```
4. The Swagger Api documentation can be found on 
    ```http://localhost:8080/swagger-ui.html```