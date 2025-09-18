import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileWriterTest {

    public static void testHashFileKnown() throws IOException {
        Path known = Files.createTempFile("known", ".txt");
        Files.write(known, "abc".getBytes(StandardCharsets.UTF_8));
        String hash = MyFileWriter.hashFile(known.toString());
        System.out.println("Known test (abc): " + hash);
    }

    public static void testHashFileEmptyFiles() throws IOException {
        Path empty = Files.createTempFile("empty", ".txt");
        String hash = MyFileWriter.hashFile(empty.toString());
        System.out.println("Empty file: " + hash);
    }

    public static void testHashFileLargeFiles() throws IOException {
        Path large = Files.createTempFile("large", ".bin");
        byte[] block = new byte[1024];
        for (int i = 0; i < 1024; i++) {
            block[i] = (byte) (i & 0xff);
        }
        for (int i = 0; i < 1000; i++) {
            Files.write(large, block, java.nio.file.StandardOpenOption.APPEND);
        }
        String hash = MyFileWriter.hashFile(large.toString());
        System.out.println("Large file: " + hash);
    }

    public static void testHashFileSpecialChars() throws IOException {
        Path special = Files.createTempFile("special", ".txt");
        String content = "漢字かなカナ";
        Files.write(special, content.getBytes(StandardCharsets.UTF_8));
        String hash = MyFileWriter.hashFile(special.toString());
        System.out.println("Special chars: " + hash);
    }

    public static void testHashFileNonExistent() {
        try {
            MyFileWriter.hashFile("this_file_does_not_exist.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Non-existent file: exception thrown as expected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            testHashFileKnown();
            testHashFileEmptyFiles();
            testHashFileLargeFiles();
            testHashFileSpecialChars();
            testHashFileNonExistent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
