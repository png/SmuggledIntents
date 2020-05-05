const express = require('express');
const server = express();
const port = 8000;

server.get("/", (req, res) => {
   console.log("Contact:", req.query)
   res.sendFile(__dirname + '/index.html');
});

server.listen(port, () => {
    console.log(`Server listening at ${port}`);
});
