Welcome to the TemporaryGroup UML Editor.

To be able to run the program, please follow these steps. 

1. Open terminal of choice

2. Download java development kit from https://www.oracle.com/java/technologies/downloads/

3. Install java development kit

4. Clone repository from github at https://github.com/mucsci-students/2022sp-420-TemporaryGroup 

5. Download external JARs needed from https://www.dropbox.com/s/79h41e6ondew84t/External%20Libraries%20for%20Sprint%201.zip?dl=0

6. Keep .jar and .java files on same directory 

7. Create a directory to save .class files (optional)

8. compile editor with command
    
    (on linux or mac) : javac -cp gson-2.8.9.jar:json-simple1.1.1.jar -d "path to classes directory e.g /home/usr/johnDoe/Documents/umleditor" umlcli.java UMLDiagram.java UMLClass.java UMLRelationship.java Load.java Save.java

    (on windows) : javac -cp gson-2.8.9.jar`;json-simple1.1.1.jar -d "path to classes directory e.g. C:\Users\johnDoe\Documents\umleditor" umlcli.java UMLDiagram.java UMLClass.java UMLRelationship.java Load.java Save.java

    note: if step 7 was skipped no need to use -d flag

9. run editor with command 

    (on linux or mac) : java -cp "path to classes directory e.g /home/usr/johnDoe/Documents/umleditor":gson-2.8.9.jar:json-simple.1.1.1.jar umlcli

    (on windows) : java -cp "path to classes directory e.g C:\Users\johnDoe\Documents\umleditor"`;gson-2.8.9.jar`;json-simple.1.1.1.jar umlcli

    note: if step 7 was skipped no need to use -d flag

10. Have fun with your command line UML editor!