// const express = require('express');
// const { exec } = require('child_process');
// const path = require('path');
// const app = express();
// const port = 3000;

// app.use(express.static(path.join(__dirname, 'public')));

// app.get('/run-java', (req, res) => {
//     exec('java -jar ../Framed.jar', (error, stdout, stderr) => {
//         if (error) {
//             console.error(`exec error: ${error}`);
//             return res.status(500).send('Error running Java application');
//         }
//         res.send(`Output: ${stdout}`);
//     });
// });

// app.listen(port, () => {
//     console.log(`Server listening at http://localhost:${port}`);
// });



// no pop out versoion
const express = require('express');
const { exec } = require('child_process');
const path = require('path');
const app = express();
const port = 3000;

app.use(express.static(path.join(__dirname, 'public')));

app.get('/run-java', (req, res) => {
    exec('java -jar ../Framed.jar', (error, stdout, stderr) => {
        if (error) {
            console.error(`exec error: ${error}`);
            return res.status(500).send(`Error running Java application: ${error.message}`);
        }
        if (stderr) {
            console.error(`stderr: ${stderr}`);
            return res.status(500).send(`Java application error: ${stderr}`);
        }
        res.send(stdout);
    });
});

app.listen(port, () => {
    console.log(`Server listening at http://localhost:${port}`);
});
