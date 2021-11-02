package frontend;

import com.sun.deploy.net.MessageHeader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class RightPanel extends JPanel {

    private final JPanel sourcePanel;
    private final JPanel destinationPanel;
    private final JComboBox sourceSelector;
    private final JComboBox destinationSelector;
    private final JPanel algorithmPanel;
    private final JRadioButton dijkstraButton;
    private final JRadioButton aStarButton;
    private final JRadioButton bellmanButton;
    private final JButton parseButton;
    private final JButton parseButton2;
    private final JButton solveButton;

    public RightPanel() {
        sourceSelector = new JComboBox();
        destinationSelector = new JComboBox();

        dijkstraButton = new JRadioButton("Dijkstra");
        aStarButton = new JRadioButton("A-Star");
        bellmanButton = new JRadioButton("Bellman");

        sourcePanel = new JPanel();
        destinationPanel = new JPanel();
        algorithmPanel = new JPanel();

        parseButton = new JButton("Parse file...");
        parseButton2 = new JButton("Parse file...");

        setUpSourceAndDestination();
        setUpAlgorithm();

        this.add(sourcePanel);
        this.add(destinationPanel);
        this.add(algorithmPanel);

        solveButton = new JButton("Solve");
        solveButton.setEnabled(false);
        JPanel solvePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        solvePanel.add(solveButton);
        this.add(solvePanel);

        this.setBorder(BorderFactory.createTitledBorder("Settings"));

    }

    private void setUpAlgorithm() {
        algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));
        algorithmPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        algorithmPanel.add(new JLabel("Select an algorithm:"));
        ButtonGroup group = new ButtonGroup();
        group.add(dijkstraButton);
        group.add(aStarButton);
        group.add(bellmanButton);
        dijkstraButton.setSelected(true);

        JPanel dijkstraPanel = new JPanel();
        JPanel aStarPanel = new JPanel();
        JPanel bellmanPanel = new JPanel();
        dijkstraPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        aStarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bellmanPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        dijkstraPanel.add(dijkstraButton);
        aStarPanel.add(aStarButton);
        bellmanPanel.add(bellmanButton);

        algorithmPanel.add(dijkstraPanel);
        algorithmPanel.add(aStarPanel);
        algorithmPanel.add(bellmanPanel);

    }

    private void setUpSourceAndDestination() {
        sourceSelector.setEnabled(false);
        destinationSelector.setEnabled(false);
        sourceSelector.setPreferredSize(new Dimension(110, 30));
        destinationSelector.setPreferredSize(new Dimension(110, 30));

        sourcePanel.add(new JLabel("Source Vertex: "));
        sourcePanel.add(sourceSelector);
        sourcePanel.add(parseButton);

        destinationPanel.add(new JLabel("Destination Vertex: "));
        destinationPanel.add(destinationSelector);
        destinationPanel.add(parseButton2);

        sourcePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        destinationPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        sourcePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        destinationPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }
}
