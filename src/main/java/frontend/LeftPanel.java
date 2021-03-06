package frontend;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class LeftPanel extends JPanel {

    private final JTextArea graphTextArea;

    private final JPanel separatorPanel;
    private final JPanel textPanel;
    private final JRadioButton commaButton;
    private final JRadioButton colonButton;
    private final JRadioButton semicolonButton;
    private final JRadioButton spaceButton;

    public LeftPanel() {

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.add(new JLabel("Paste your graph structure here:"));
        this.add(labelPanel);

        textPanel = new JPanel();
        graphTextArea = new JTextArea(getDefaultGraph(), 8, 30);

        separatorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        commaButton = new JRadioButton(" , ");
        colonButton = new JRadioButton(" : ");
        semicolonButton = new JRadioButton(" ; ");
        spaceButton = new JRadioButton("[space]");

        initTextPanel();
        initSeparatorPanel();

        this.setBorder(BorderFactory.createTitledBorder("Graph"));
    }

    private void initSeparatorPanel() {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(commaButton);
        buttonGroup.add(colonButton);
        buttonGroup.add(semicolonButton);
        buttonGroup.add(spaceButton);

        commaButton.setSelected(true);

        separatorPanel.add(new JLabel("Separator: "));
        separatorPanel.add(commaButton);
        separatorPanel.add(colonButton);
        separatorPanel.add(semicolonButton);
        separatorPanel.add(spaceButton);

        this.add(separatorPanel);
    }

    private void initTextPanel() {
        textPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JScrollPane scrollPane = new JScrollPane(graphTextArea);
        textPanel.add(scrollPane);
        this.add(textPanel);
    }

    private String getDefaultGraph() {
        return "A,B,2" + "\n" +
                "A,C,3" + "\n" +
                "B,C,1" + "\n" +
                "B,D,5" + "\n" +
                "D,E,1" + "\n" +
                "C,E,4";
    }

    public JTextArea getGraphTextArea() {
        return graphTextArea;
    }

    public JRadioButton getColonButton() {
        return colonButton;
    }

    public JRadioButton getSemicolonButton() {
        return semicolonButton;
    }

    public JRadioButton getSpaceButton() {
        return spaceButton;
    }

    public String getSeparator() {
        String separator = ",";
        if (getSemicolonButton().isSelected()) {
            separator = ";";
        } else if (getColonButton().isSelected()) {
            separator = ":";
        } else if (getSpaceButton().isSelected()) {
            separator = " ";
        }
        return separator;
    }
}
