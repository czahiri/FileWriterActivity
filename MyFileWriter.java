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

    public static void main(String[] args) {
        createHiddenPasswordFile();
        createConfidentialInHiddenFolder();
    }
}

