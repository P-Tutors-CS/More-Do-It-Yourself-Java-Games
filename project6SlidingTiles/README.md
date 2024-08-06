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



# project6SlidingTiles

## Project Structure
Ensure your project structure is as follows:


project6SlidingTiles/
│
├── src/
│   ├── SlidingTiles.java
│   └── TileButton.java
│
├── resources/
│   └── images/
│       └── slidingTilesImage.jpg
│
├── bin/  (compiled .class files)
│
├── manifest.txt
├── README.md
└── SlidingTiles.jar

## How to Create a Standalone Executable JAR

### Step-by-Step Instructions to Create a standalone JAR file for the `project6SlidingTiles` project.


1. **Navigate to the `src` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project6SlidingTiles/
    ```

2. **Compile the Java Files - Ensure all your Java files are compiled and located in the bin directory.**:
    ```sh
        javac -d bin src/*.java
    ```

3. **Create the Manifest File**:
    ```sh
    echo "Main-Class: SlidingTiles" > manifest.txt
    ```
    ### If you get a `java.io.IOException: invalid header field` error, just manuly create the `mainfest.txt` file

    Ensure that there is a newline at the end of the `manifest.txt` file. You can open it in a text editor and press Enter to add a newline.

6. **Create the JAR File**:
    ```sh
    jar cfm SlidingTiles.jar manifest.txt -C bin . -C resources .
    ```

7. **Ensure the JAR File Runs Correctly**:
    ```sh
    java -jar SlidingTiles.jar
    ```

8. **Create a Batch File for Windows (Optional)**:
    If you want to run the JAR file by double-clicking on Windows, you can create a batch file.
    - Open a text editor and paste the following line:
      ```sh
      @echo off
      java -jar SlidingTiles.jar
      pause
      ```
    - Save the file as `runSlidingTiles.bat` in the same directory as `SlidingTiles.jar`.

    **Create a Shortcut:**
    1. Right-click on the batch file you created.
    2. Select "Create shortcut".
    3. Drag the shortcut to your desktop.

9. **Create a Shell Script for Mac/Linux (Optional)**:
    If you want to run the JAR file by double-clicking on Mac or Linux, you can create a shell script.
    - Open a text editor and paste the following line:
      ```sh
      #!/bin/sh
      java -jar SlidingTiles.jar
      ```
    - Save the file as `runSlidingTiles.sh` in the same directory as `SlidingTiles.jar`.
    - Make the script executable:
      ```sh
      chmod +x runSlidingTiles.sh
      ```

### Notes

- Ensure you are in the correct directory before running each command.
- Replace `path/to/More-Do-It-Yourself-Java-Games` with the actual path to your project directory.
- The `manifest.txt` file specifies the entry point of the application. Make sure that the `Main-Class` attribute points to the class containing the `main` method.
- For a truly standalone JAR with dependencies, you may need to use a build tool like Maven or Gradle to create an "uber-jar" or "fat-jar". Below are brief instructions for using Maven.

### Creating an Executable JAR with Dependencies using Maven

1. **Navigate to the `project6SlidingTiles` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project6SlidingTiles
    ```

2. **Create a `pom.xml` File with the Following Content**:
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.example</groupId>
        <artifactId>SlidingTiles</artifactId>
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
                                <mainClass>SlidingTiles</mainClass>
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
    java -jar SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```

5. **Transfer the JAR File to Another Computer**:
    Copy the `SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar` file from the `target` directory to the desired location on the other computer.

6. **Run the JAR File on Another Computer**:
    Ensure that the target computer has Java installed, then run the JAR file by double-clicking it or using the command line:
    ```sh
    java -jar SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar
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
  java -jar SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar



---

<br>
<br>

---

  To run your Java application from a web interface both locally and when deployed to GitHub Pages and Heroku, you need to create a web application that can interact with your Java code. We will use a simple setup with Java, HTML, and a lightweight server like Spark for the backend. Here's how you can do it:

### 1. Setting Up Your Project for Web Interface

#### Step-by-Step Instructions

1. **Add a New Directory for Web Content**
    - Navigate to your project directory:
      ```sh
      cd path/to/More-Do-It-Yourself-Java-Games/project6SlidingTiles
      ```
    - Create a new directory for your web content:
      ```sh
      mkdir web
      ```

2. **Create a Basic HTML File**
    - Navigate to the `web` directory:
      ```sh
      cd web
      ```
    - Create an `index.html` file with a basic structure:
      ```html
      <!DOCTYPE html>
      <html lang="en">
      <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Watch Your Step</title>
      </head>
      <body>
          <h1>Watch Your Step</h1>
          <button id="startButton">Start Game</button>
          <div id="gameOutput"></div>
          <script>
              document.getElementById('startButton').addEventListener('click', function() {
                  fetch('/start')
                      .then(response => response.text())
                      .then(data => {
                          document.getElementById('gameOutput').innerText = data;
                      });
              });
          </script>
      </body>
      </html>
      ```

3. **Create a Simple Java Web Server**
    - Navigate back to your project root directory:
      ```sh
      cd ..
      ```
    - Add a dependency for Spark in your `pom.xml`:
      ```xml
      <dependencies>
          <dependency>
              <groupId>com.sparkjava</groupId>
              <artifactId>spark-core</artifactId>
              <version>2.9.3</version>
          </dependency>
          <!-- Add other dependencies here -->
      </dependencies>
      ```
    - Create a new Java file `WebServer.java` in the `src` directory:
      ```java
      import static spark.Spark.*;

      public class WebServer {
          public static void main(String[] args) {
              staticFiles.location("/web"); // Serve static files from the web directory

              get("/start", (req, res) -> {
                  return "Game started!";
              });
          }
      }
      ```

4. **Compile and Run Your Web Server Locally**
    - Compile the Java files and include the dependencies:
      ```sh
      mvn clean package
      ```
    - Run the web server:
      ```sh
      java -cp target/SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar WebServer
      ```
    - Open a web browser and go to `http://localhost:4567` to see your web interface.

### 2. Deploying to GitHub Pages

GitHub Pages is used for static sites. You can host your HTML, CSS, and JavaScript there.

1. **Create a `gh-pages` Branch**
    - In your project root directory:
      ```sh
      git checkout --orphan gh-pages
      git rm -rf .
      git commit --allow-empty -m "Initializing gh-pages branch"
      git push origin gh-pages
      git checkout main
      ```

2. **Add HTML Content to GitHub Pages**
    - Copy your `web` directory content to the root of the `gh-pages` branch:
      ```sh
      git checkout gh-pages
      cp -r web/* .
      git add .
      git commit -m "Add web content"
      git push origin gh-pages
      git checkout main
      ```

3. **Enable GitHub Pages**
    - Go to your GitHub repository settings.
    - Scroll down to the "GitHub Pages" section.
    - Select `gh-pages` branch as the source.

Now, your static web interface should be available at `https://yourusername.github.io/your-repo-name`.

### 3. Deploying to Heroku

Heroku can run your Java web server and serve your application.

1. **Create a `Procfile` for Heroku**
    - In your project root directory, create a `Procfile` with the following content:
      ```sh
      web: java -cp target/SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar WebServer
      ```

2. **Add a System.properties File**
    - In the project root directory, create a `system.properties` file to specify the Java runtime:
      ```sh
      java.runtime.version=11
      ```

3. **Deploy to Heroku**
    - Install the Heroku CLI and log in:
      ```sh
      heroku login
      ```
    - Create a new Heroku app:
      ```sh
      heroku create your-app-name
      ```
    - Add a Git remote for Heroku:
      ```sh
      git remote add heroku https://git.heroku.com/your-app-name.git
      ```
    - Push your code to Heroku:
      ```sh
      git push heroku main
      ```
    - Open your Heroku app in a web browser:
      ```sh
      heroku open
      ```

### Summary of Directory Structure

```plaintext
project6SlidingTiles/
│
├── bin/
│   └── *.class
├── src/
│   ├── SlidingTiles.java
│   ├── WebServer.java
│   └── ...
├── web/
│   └── index.html
├── target/
│   └── SlidingTiles-1.0-SNAPSHOT-jar-with-dependencies.jar
├── pom.xml
├── manifest.txt
├── Procfile
├── system.properties
└── ...
```

### Final Notes

- Ensure that your web server serves static files correctly.
- Test your web interface locally before deploying.
- Make sure to replace placeholders (e.g., `your-app-name`) with actual values.

By following these instructions, you'll be able to run your Java application both locally and on GitHub Pages for the static part and Heroku for the dynamic part.




---


# if imageUrl image issues:

### Step 1: Verify Project Structure
Ensure your project structure is correctly set up as follows:

```
project6SlidingTiles/
│
├── src/
│   ├── SlidingTiles.java
│   └── TileButton.java
│
├── resources/
│   └── images/
│       └── slidingTilesImage.jpg
│
├── bin/  (compiled .class files)
│
├── manifest.txt
├── README.md
└── SlidingTiles.jar
```

### Step 2: Update Code to Load Resource

Make sure your `SlidingTiles.java` code correctly uses `getResource` to load the image. Here’s a corrected version of your code:

```java
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SlidingTiles {
    ...
    // example 
    private void loadResources() {
        try {
            // Use getResource to find the image
            img = ImageIO.read(SlidingTiles.class.getResource("/images/slidingTilesImage.jpg"));
            if (img == null) {
                System.err.println("Image not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ...
}
```

### Step 3: Update `launch.json` in VS Code

Ensure you have a `launch.json` file in the `.vscode` directory that includes the `resources` directory in the classpath. If the file does not exist, create it.

#### Example `launch.json`:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch SlidingTiles",
            "request": "launch",
            "mainClass": "SlidingTiles",
            "projectName": "More-Do-It-Yourself-Java-Games",
            "classPaths": [
                "${workspaceFolder}/bin",
                "${workspaceFolder}/resources"
            ]
        }
    ]
}
```

### Step 4: Update `settings.json` to Include Resources

Ensure `settings.json` in the `.vscode` directory includes the `resources` directory.

#### Example `settings.json`:
```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "resources"
    ]
}
```

### Step 5: Clean and Rebuild the Project

1. **Clean the Project**:
   - Delete the contents of the `bin` directory to ensure a clean build.

2. **Rebuild the Project**:
   - Compile your project again using the build tasks in VS Code or manually using `javac`.

```sh
javac -d bin src/*.java
```

### Step 6: Run the Project

Use the play button in the Debug panel to run your project. The `launch.json` configuration should now ensure the resources are correctly included in the classpath.

