package backend.controller;

import backend.model.api.ALGORITHM;
import backend.model.api.Result;
import backend.model.api.SolverAPI;
import frontend.LeftPanel;
import frontend.ResultPanel;
import frontend.RightPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SolverController implements ActionListener {
    private final LeftPanel leftPanel;
    private final RightPanel rightPanel;
    private final ResultPanel resultPanel;

    public SolverController(LeftPanel leftPanel, RightPanel rightPanel, ResultPanel resultPanel) {
        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;
        this.resultPanel = resultPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> inputGraph = Arrays.asList(leftPanel.getGraphTextArea().getText().split("\n"));
        String separator = leftPanel.getSeparator();
        String source = "" + rightPanel.getSourceSelector().getSelectedItem();
        String dest = "" + rightPanel.getDestinationSelector().getSelectedItem();
        ALGORITHM algorithm = rightPanel.getAlgorithm();
        Result solve = SolverAPI.solve(inputGraph, source, dest, separator, algorithm);
        resultPanel.updateMessage(solve.getMessage(), solve.isError());
    }
}
