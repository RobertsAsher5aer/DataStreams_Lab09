import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchPanel extends JPanel {
    private JTextField searchField;
    private TextFileSearchFrame parentFrame;

    public SearchPanel(TextFileSearchFrame frame) {
        this.parentFrame = frame;
        setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        JButton loadButton = new JButton("Load File");
        JButton searchButton = new JButton("Search");
        JButton quitButton = new JButton("Quit");

        // Add components to the panel
        add(searchLabel);
        add(searchField);
        add(loadButton);
        add(searchButton);
        add(quitButton);

        // Button Listeners
        loadButton.addActionListener(this::loadFile);
        searchButton.addActionListener(this::searchFile);
        quitButton.addActionListener(e -> System.exit(0));
    }

    private void loadFile(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            parentFrame.loadFile(fileChooser.getSelectedFile().toPath());
        }
    }

    private void searchFile(ActionEvent event) {
        String searchString = searchField.getText().trim();
        parentFrame.searchFile(searchString);
    }
}
