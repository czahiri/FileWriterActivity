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
private static void printFileSize(String... fileNames) {
    long totalSize = 0;
    for (String fileName : fileNames) {
        File file = new File(fileName);
        if (file.exists()) {
            totalSize += file.length();
        }
    }
    System.out.println("Total size of all files: " + totalSize + " bytes");
}

public static String toString(InputStream input) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append(System.lineSeparator());
            }
        }
    return sb.toString();
    }
    public static void main(String[] args) {
        createHiddenPasswordFile();
        createConfidentialInHiddenFolder();
        printFileSize("HelloWorld.txt", ".secretpassword.txt");
    }
}

