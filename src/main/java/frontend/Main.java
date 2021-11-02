package frontend;

import javax.swing.*;

public class Main {

    private final JFrame window;
    private final JPanel body;
    private final JPanel leftPanel;
    private final RightPanel rightPanel;

    public Main() {
        window = new JFrame("Path Finder");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        body = new JPanel();

        leftPanel = new LeftPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        rightPanel = new RightPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


        body.add(leftPanel);
        body.add(rightPanel);

        window.add(body);
        window.pack();
        window.setVisible(true);
    }
}
