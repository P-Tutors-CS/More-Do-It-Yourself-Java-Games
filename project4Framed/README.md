## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


# project4Framed

## How to Create a Standalone Executable JAR

### Step-by-Step Instructions to Create a standalone JAR file for the `project4Framed` project.


1. **Navigate to the `src` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project4Framed/
    ```

2. **Compile the Java Files - Ensure all your Java files are compiled and located in the bin directory.**:
    ```sh
        javac -d bin src/*.java
    ```

3. **Create the Manifest File**:
    ```sh
    echo "Main-Class: Framed" > manifest.txt
    ```
    ### If you get a `java.io.IOException: invalid header field` error, just manuly create the `mainfest.txt` file

    Ensure that there is a newline at the end of the `manifest.txt` file. You can open it in a text editor and press Enter to add a newline.

6. **Create the JAR File**:
    ```sh
    jar cfm Framed.jar manifest.txt -C bin .
    ```

7. **Ensure the JAR File Runs Correctly**:
    ```sh
    java -jar Framed.jar
    ```

8. **Create a Batch File for Windows (Optional)**:
    If you want to run the JAR file by double-clicking on Windows, you can create a batch file.
    - Open a text editor and paste the following line:
      ```sh
      @echo off
      java -jar Framed.jar
      pause
      ```
    - Save the file as `runFramed.bat` in the same directory as `Framed.jar`.

    **Create a Shortcut:**
    1. Right-click on the batch file you created.
    2. Select "Create shortcut".
    3. Drag the shortcut to your desktop.

9. **Create a Shell Script for Mac/Linux (Optional)**:
    If you want to run the JAR file by double-clicking on Mac or Linux, you can create a shell script.
    - Open a text editor and paste the following line:
      ```sh
      #!/bin/sh
      java -jar Framed.jar
      ```
    - Save the file as `runFramed.sh` in the same directory as `Framed.jar`.
    - Make the script executable:
      ```sh
      chmod +x runFramed.sh
      ```

### Notes

- Ensure you are in the correct directory before running each command.
- Replace `path/to/More-Do-It-Yourself-Java-Games` with the actual path to your project directory.
- The `manifest.txt` file specifies the entry point of the application. Make sure that the `Main-Class` attribute points to the class containing the `main` method.
- For a truly standalone JAR with dependencies, you may need to use a build tool like Maven or Gradle to create an "uber-jar" or "fat-jar". Below are brief instructions for using Maven.

### Creating an Executable JAR with Dependencies using Maven

1. **Navigate to the `project4Framed` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project4Framed
    ```

2. **Create a `pom.xml` File with the Following Content**:
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.example</groupId>
        <artifactId>Framed</artifactId>
        <version>1.0-SNAPSHOT</version>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <version>3.3.0</version>
                    <configuration>
                        <archive>
                            <manifest>
                                <mainClass>Framed</mainClass>
                            </manifest>
                        </archive>
                        <descriptorRefs>
                            <descriptorRef>jar-with-dependencies</descriptorRef>
                        </descriptorRefs>
                    </configuration>
                    <executions>
                        <execution>
                            <id>make-assembly</id>
                            <phase>package</phase>
                            <goals>
                                <goal>single</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
        <dependencies>
            <!-- Add your project dependencies here -->
        </dependencies>
    </project>
    ```

3. **Package the Project using Maven**:
    ```sh
    mvn clean package
    ```

4. **The JAR File will be Created in the `target` Directory**:
    ```sh
    cd target
    java -jar Framed-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

5. **Transfer the JAR File to Another Computer**:
    Copy the `Framed-1.0-SNAPSHOT-jar-with-dependencies.jar` file from the `target` directory to the desired location on the other computer.

6. **Run the JAR File on Another Computer**:
    Ensure that the target computer has Java installed, then run the JAR file by double-clicking it or using the command line:
    ```sh
    java -jar Framed-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

### Notes

- Ensure you are in the correct directory before running each command.
- Replace `path/to/More-Do-It-Yourself-Java-Games` with the actual path to your project directory.
- The `pom.xml` file specifies the entry point of the application and includes necessary plugins to package the JAR with dependencies.
- This method ensures that all dependencies are included in the JAR file, making it portable and executable on another computer with Java installed.

### Optional: Creating a Batch File for Windows and Shell Script for Mac/Linux

**Batch File for Windows**:
- Open a text editor and paste the following line:
  ```sh
  java -jar Framed-1.0-SNAPSHOT-jar-with-dependencies.jar



---

<br>
<br>

---


# Running Java Application from a Web Interface

This guide explains how to run a Java application from a web interface using Node.js for the backend and HTML, CSS, and JavaScript for the frontend. The backend server executes the Java application and returns the output to be displayed on the webpage.

## Project Structure

```
project4Framed/
├── .vscode/
├── bin/
│   ├── Framed.class
│   ├── Framed$1.class
│   ├── Framed$2.class
│   ├── LightButton.class
├── src/
│   ├── Framed.java
│   ├── LightButton.java
├── node-server/
│   ├── public/
│   │   └── index.html
│   ├── server.js
│   ├── package.json
│   ├── package-lock.json
├── Framed.bat
├── Framed.jar
├── manifest.txt
└── README.md
```

## Step-by-Step Instructions

### 1. Set Up Node.js Backend

1. **Create the `node-server` Directory:**

   Inside your `project4Framed` directory, create a new directory named `node-server`.

   ```sh
   mkdir node-server
   cd node-server
   ```

2. **Initialize Node.js Project:**

   Inside the `node-server` directory, initialize a new Node.js project and install `express`.

   ```sh
   npm init -y
   npm install express
   ```

3. **Create `server.js`:**

   Inside the `node-server` directory, create a `server.js` file with the following content:

   ```js
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
   ```

### 2. Create Frontend HTML, CSS, and JavaScript

1. **Create `public/index.html`:**

   Inside the `node-server` directory, create a `public` directory. Inside `public`, create an `index.html` file with the following content:

   ```html
   <!DOCTYPE html>
   <html lang="en">
   <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Run Java Application</title>
     <style>
       body {
         font-family: Arial, sans-serif;
         text-align: center;
         padding: 20px;
       }
       button {
         padding: 10px 20px;
         font-size: 16px;
         margin-bottom: 20px;
       }
       #output {
         white-space: pre-wrap; /* Preserve whitespace and line breaks */
         border: 1px solid #ccc;
         padding: 10px;
         text-align: left;
         max-width: 600px;
         margin: 0 auto;
         background-color: #f9f9f9;
       }
     </style>
   </head>
   <body>
     <h1>Run Java Application from Web</h1>
     <button onclick="runJavaApp()">Run Java Application</button>
     <div id="output"></div>

     <script>
       function runJavaApp() {
         fetch('/run-java')
           .then(response => response.text())
           .then(data => {
             document.getElementById('output').innerText = data;
           })
           .catch(error => {
             document.getElementById('output').innerText = `Error: ${error}`;
           });
       }
     </script>
   </body>
   </html>
   ```

### 3. Running Locally

1. **Navigate to your project directory:**

   ```sh
   cd path/to/your/project4Framed/node-server
   ```

2. **Start the server:**

   ```sh
   node server.js
   ```

3. **Open the Web Interface:**

   Open your browser and navigate to `http://localhost:3000`. You should see a button to run your Java application, and the output will be displayed directly within the page.

### 4. Deploying to Heroku and GitHub Pages

#### Deploy Backend to Heroku

1. **Install Heroku CLI:**

   Follow the instructions on the [Heroku CLI installation page](https://devcenter.heroku.com/articles/heroku-cli) to install the Heroku CLI.

2. **Create a Heroku Account:**

   If you don't have one, create a Heroku account at [Heroku](https://www.heroku.com/).

3. **Prepare Your Node.js Backend:**

   Ensure your `node-server` directory is ready for deployment with a `Procfile`.

   - **Procfile:**

     Create a file named `Procfile` in the `node-server` directory with the following content:

     ```sh
     web: node server.js
     ```

4. **Deploy to Heroku:**

   - Navigate to your `node-server` directory in the terminal.
   - Login to Heroku:

     ```sh
     heroku login
     ```

   - Create a new Heroku app:

     ```sh
     heroku create
     ```

   - Deploy your app to Heroku:

     ```sh
     git init
     git add .
     git commit -m "Initial commit"
     heroku git:remote -a your-heroku-app-name
     git push heroku master
     ```

   Replace `your-heroku-app-name` with the name of your Heroku app.

5. **Verify Deployment:**

   Once deployed, you should be able to access your backend at `https://your-heroku-app-name.herokuapp.com`.

#### Deploy Frontend to GitHub Pages

1. **Prepare Your Frontend:**

   Ensure your frontend HTML, CSS, and JavaScript files are ready in a directory, for example, `public`.

2. **Create a GitHub Repository:**

   - Go to GitHub and create a new repository.
   - Clone the repository to your local machine:

     ```sh
     git clone https://github.com/your-username/your-repository.git
     cd your-repository
     ```

3. **Add Frontend Files to Repository:**

   Copy your frontend files to the repository directory:

   ```sh
   cp -r path/to/your/node-server/public/* .
   ```

4. **Commit and Push to GitHub:**

   - Add, commit, and push your changes:

     ```sh
     git add .
     git commit -m "Deploy frontend"
     git push origin main
     ```

5. **Enable GitHub Pages:**

   - Go to your repository settings on GitHub.
   - Scroll down to the "GitHub Pages" section.
   - Select the source branch (`main` or `master`) and the root directory `/`.
   - Save the settings. Your site will be published at `https://your-username.github.io/your-repository`.

6. **Update Frontend to Point to Heroku Backend:**

   In your `index.html` hosted on GitHub Pages, update the fetch URL to point to your Heroku backend:

   ```html
   <script>
     function runJavaApp() {
       fetch('https://your-heroku-app-name.herokuapp.com/run-java')
         .then(response => response.text())
         .then(data => {
           document.getElementById('output').innerText = data;
         })
         .catch(error => {
           document.getElementById('output').innerText = `Error: ${error}`;
         });
     }
   </script>
   ```



---