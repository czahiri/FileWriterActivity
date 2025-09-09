import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class MyFileWriter {
    public static void createHiddenPasswordFile() {
        Path p = Paths.get(".secretpassword.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            bw.write("My$uperSecretP@ss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createConfidentialInHiddenFolder() {
        Path p = Paths.get(".classified", "confidential.dat");
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            bw.write("Confidential information goes here.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calculate and print the file size using the File class
private static void printFileSize(String fileName) {
    // Implementation goes here
    File file = new File(fileName);
    if (file.exists() && file.isFile()) {
        System.out.println("File size of " + fileName + ": " + file.length() + " bytes");
    } else {
        System.out.println("File " + fileName + " does not exist."); 
    }
}

    public static void main(String[] args) {
        createHiddenPasswordFile();
        createConfidentialInHiddenFolder();
    }
}

