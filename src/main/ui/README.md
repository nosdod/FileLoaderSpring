# Dashboard template

## Design

The basis for the code here is one of the @mui examples - A dashboard
View the demo of that at https://mui.com/getting-started/templates/dashboard/.
The code has been heavily modified.

## Demo
The 'look' of it now mimics the existing Entropy Loader - at least what I think the Entropy Loader looks like from examining the C# source code Dave gave me!
Opening and Closing the Tool Draw on the left adds 10k to the entropy size .... for demo purposes.
There is a settings.json file to hold the backend endpoint details
The server side is implemented in a separate NodeJS project at the moment.

See https://mui.com for more on the variety of controls etc that are in @mui/material

Material-UI is just one of many UI libraries available.

## To run it
npm install

npm start

Open a browser at http://localhost:3000

## Screenshot
See Screenshot Entropy Loader.png

## Gauge Concept
Instead of the Colour changing Chip, we could imporve the UI with a Gauge.
Navigate to http://localhost:3000/gauge-concept.html to see the concept.