package backend.controller;

import backend.model.api.SolverAPI;
import frontend.LeftPanel;
import frontend.Main;
import frontend.RightPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ParseController implements ActionListener {
    private final LeftPanel leftPanel;
    private final RightPanel rightPanel;
    private final Main parent;

    public ParseController(LeftPanel leftPanel, RightPanel rightPanel, Main parent) {
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = leftPanel.getGraphTextArea().getText();
        String separator = leftPanel.getSeparator();

        List<String> verticesFromGraphText = SolverAPI.getVerticesFromGraphText(text, separator);

        this.rightPanel.getSourceSelector().setModel(new DefaultComboBoxModel<>(verticesFromGraphText.toArray()));
        this.rightPanel.getDestinationSelector().setModel(new DefaultComboBoxModel<>(verticesFromGraphText.toArray()));

        this.rightPanel.getSourceSelector().setEnabled(!verticesFromGraphText.isEmpty());
        this.rightPanel.getDestinationSelector().setEnabled(!verticesFromGraphText.isEmpty());

        this.parent.pack();
    }
}
