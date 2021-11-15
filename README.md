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