import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    private static void printFileSize(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            System.out.println("File size of " + fileName + ": " + file.length() + " bytes");
        } else {
            System.out.println("File " + fileName + " does not exist."); 
        }
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

    public static String hashFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available");
        }

        InputStream fis = Files.newInputStream(path);
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }
        fis.close();

        byte[] hashBytes = md.digest();
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hashBytes.length; i++) {
            String hex = Integer.toHexString(0xff & hashBytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        createHiddenPasswordFile();
        createConfidentialInHiddenFolder();
    }
}
