# FileLoaderSpring

A File Loader Spring5 boot application.

Runs on localhost:8080

Provides the following endpoints

GET /api/v1/entropy which returns a JSON object with the following properties bytecount, status and filename.
Return values represent the current status of the given file.
bytecount - Size of file in bytes
status - CRITICAL, WARNING, NORMAL its size relative to configured thresholds
filename - full path to the file

POST /api/v1/entropy which accepts a POST body containing a file. The given file will overwrite the configured filename above.

POST /api/v1/credentials checks for valid credentials.

GET /swagger_ui.html Swagger UI for all available endpoints

## UI Integration
The ReactUIConcept is now included in this repository (See src/main/ui). Maven will build this 
along with the server and include it in the jar. 

Open a browser at http://localhost:8080 to get the UI.

## To build the Application
./mvnw clean package

## Windows service wrapper
To run the built jar file as a windows service, see
https://www.baeldung.com/spring-boot-app-as-a-service

Here we are using the configured 'winsw' (https://github.com/kohsuke/winsw) option described.

./FileLoaderSpring.exe install
./FileLoaderSpring.exe start
You can also use
./FileLoaderSpring.exe stop to stop the service
./FileLoaderSpring.exe uninstall to remove the service

## Environment setup
The default configuration assumes C:/UploadDir/entropy.dat for the file. 
This will be created if it doesn't exist when the application starts up.

To create example files to try out the upload functionality, you can use, for example 
fsutil file createNew entropy_20MB.dat 20000000
