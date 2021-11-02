package frontend;

import backend.controller.ParseController;
import backend.controller.SolverController;
import backend.model.api.ALGORITHM;

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

    private final ResultPanel resultPanel;

    private final Main parent;

    public RightPanel(Main parent) {
        this.parent = parent;

        resultPanel = new ResultPanel(parent);
        resultPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        resultPanel.setVisible(false);

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
        JPanel solvePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        solvePanel.add(resultPanel);
        solvePanel.add(solveButton);
        this.add(solvePanel);

        this.setBorder(BorderFactory.createTitledBorder("Settings"));
        addListeners();
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanelContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));

        buttonPanel.add(dijkstraButton);
        buttonPanel.add(aStarButton);
        buttonPanel.add(bellmanButton);

        buttonPanelContainer.add(buttonPanel);
        algorithmPanel.add(buttonPanelContainer);

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

    private void addListeners() {
        parseButton.addActionListener(new ParseController(parent.getLeftPanel(), this, this.parent));
        parseButton2.addActionListener(new ParseController(parent.getLeftPanel(), this, this.parent));
        solveButton.addActionListener(new SolverController(parent.getLeftPanel(), this, getResultPanel()));
    }

    public JComboBox getSourceSelector() {
        return sourceSelector;
    }

    public JComboBox getDestinationSelector() {
        return destinationSelector;
    }

    public JRadioButton getaStarButton() {
        return aStarButton;
    }

    public JRadioButton getBellmanButton() {
        return bellmanButton;
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }

    public ALGORITHM getAlgorithm() {
        ALGORITHM algo = ALGORITHM.DIJKSTRA;
        if (getBellmanButton().isSelected()) {
            algo = ALGORITHM.BELLMAN;
        } else if (getaStarButton().isSelected()) {
            algo = ALGORITHM.A_STAR;
        }
        return algo;
    }
}
