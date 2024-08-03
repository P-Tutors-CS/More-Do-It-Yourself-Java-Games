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


# project5WatchYourStep

## How to Create a Standalone Executable JAR

### Step-by-Step Instructions to Create a standalone JAR file for the `project5WatchYourStep` project.


1. **Navigate to the `src` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project5WatchYourStep/
    ```

2. **Compile the Java Files - Ensure all your Java files are compiled and located in the bin directory.**:
    ```sh
        javac -d bin src/*.java
    ```

3. **Create the Manifest File**:
    ```sh
    echo "Main-Class: WatchYourStep" > manifest.txt
    ```
    ### If you get a `java.io.IOException: invalid header field` error, just manuly create the `mainfest.txt` file

    Ensure that there is a newline at the end of the `manifest.txt` file. You can open it in a text editor and press Enter to add a newline.

6. **Create the JAR File**:
    ```sh
    jar cfm WatchYourStep.jar manifest.txt -C bin .
    ```

7. **Ensure the JAR File Runs Correctly**:
    ```sh
    java -jar WatchYourStep.jar
    ```

8. **Create a Batch File for Windows (Optional)**:
    If you want to run the JAR file by double-clicking on Windows, you can create a batch file.
    - Open a text editor and paste the following line:
      ```sh
      @echo off
      java -jar WatchYourStep.jar
      pause
      ```
    - Save the file as `runWatchYourStep.bat` in the same directory as `WatchYourStep.jar`.

    **Create a Shortcut:**
    1. Right-click on the batch file you created.
    2. Select "Create shortcut".
    3. Drag the shortcut to your desktop.

9. **Create a Shell Script for Mac/Linux (Optional)**:
    If you want to run the JAR file by double-clicking on Mac or Linux, you can create a shell script.
    - Open a text editor and paste the following line:
      ```sh
      #!/bin/sh
      java -jar WatchYourStep.jar
      ```
    - Save the file as `runWatchYourStep.sh` in the same directory as `WatchYourStep.jar`.
    - Make the script executable:
      ```sh
      chmod +x runWatchYourStep.sh
      ```

### Notes

- Ensure you are in the correct directory before running each command.
- Replace `path/to/More-Do-It-Yourself-Java-Games` with the actual path to your project directory.
- The `manifest.txt` file specifies the entry point of the application. Make sure that the `Main-Class` attribute points to the class containing the `main` method.
- For a truly standalone JAR with dependencies, you may need to use a build tool like Maven or Gradle to create an "uber-jar" or "fat-jar". Below are brief instructions for using Maven.

### Creating an Executable JAR with Dependencies using Maven

1. **Navigate to the `project5WatchYourStep` Directory**:
    ```sh
    cd path/to/More-Do-It-Yourself-Java-Games/project5WatchYourStep
    ```

2. **Create a `pom.xml` File with the Following Content**:
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.example</groupId>
        <artifactId>WatchYourStep</artifactId>
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
                                <mainClass>WatchYourStep</mainClass>
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
    java -jar WatchYourStep-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```