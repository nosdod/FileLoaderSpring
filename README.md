# FileLoaderSpring

A File Loader Spring5 boot application.

Runs on localhost:8080

Provides the following endpoints

/api/v1/entropy which returns a JSON object with the following properties bytecount, status and filename.
Return values represent the current status of the given file.
bytecount - Size of file in bytes
status - CRITICAL, WARNING, NORMAL its size relative to configured thresholds
filename - full path to the file

/api/v1/uploadEntropy which accepts a POST body containg a file. The given file will overwrite the configured filename above.
