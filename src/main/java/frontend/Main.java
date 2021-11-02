package frontend;

import javax.swing.*;

public class Main {

    private final JFrame window;
    private final JPanel body;
    private final JPanel leftPanel;
    private final RightPanel rightPanel;
    private final JTextArea graphTextArea;



    public Main() {
        window = new JFrame("Path Finder");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        body = new JPanel();

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        rightPanel = new RightPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        graphTextArea = new JTextArea("Upload a graph file",30, 50);
        graphTextArea.setEditable(false);
        leftPanel.add(graphTextArea);

        body.add(leftPanel);
        body.add(rightPanel);

        window.add(body);
        window.pack();
        window.setVisible(true);
    }
}
