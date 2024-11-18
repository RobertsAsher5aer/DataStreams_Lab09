import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Path;

public class TextFileSearchFrame extends JFrame {
    private JTextArea originalTextArea;
    private JTextArea filteredTextArea;
    private JTextField searchField;
    private Path filePath;

    public TextFileSearchFrame() {
        // Frame setup
        setTitle("Text File Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 240);
        setLayout(new BorderLayout());

        // Initialize text areas and split pane
        originalTextArea = new JTextArea();
        originalTextArea.setEditable(false);
        JScrollPane originalScrollPane = new JScrollPane(originalTextArea);

        filteredTextArea = new JTextArea();
        filteredTextArea.setEditable(false);
        JScrollPane filteredScrollPane = new JScrollPane(filteredTextArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, originalScrollPane, filteredScrollPane);
        splitPane.setDividerLocation(400);

        // Initialize search panel
        SearchPanel searchPanel = new SearchPanel(this);

        // Add components to frame
        add(splitPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void loadFile(Path path) {
        filePath = path;
        String content = FileLoader.loadFileContent(filePath);
        if (content != null) {
            originalTextArea.setText(content);
            filteredTextArea.setText(""); // Clear filtered text area
        }
    }

    public void searchFile(String searchString) {
        if (filePath == null) {
            JOptionPane.showMessageDialog(this, "Please load a file first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (searchString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search string.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String filteredContent = FileLoader.searchFileContent(filePath, searchString);
        if (filteredContent != null) {
            filteredTextArea.setText(filteredContent);
        }
    }
}

