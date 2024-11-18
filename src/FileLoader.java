import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoader {

    public static String loadFileContent(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            // Collect lines and join them with newline characters to preserve line breaks
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static String searchFileContent(Path filePath, String searchString) {
        try (Stream<String> lines = Files.lines(filePath)) {
            // Filter lines that contain the search string and join with newline characters
            return lines.filter(line -> line.contains(searchString))
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
