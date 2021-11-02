package frontend;

import javax.swing.*;

public class ResultPanel extends JPanel {

    private JLabel resultLabel;
    private Main parent;

    public ResultPanel(Main parent) {
        this.parent = parent;
        this.resultLabel = new JLabel(" ");
        this.add(resultLabel);
    }


    public void updateMessage(String message, boolean isError){
        if(isError){
            resultLabel.setText("<html><font color=red>" + message + "</color></html>");
        } else {
            resultLabel.setText("<html>The shortest path is: <font color=blue>" + message + "</color></html>");
        }
        this.setVisible(true);
        parent.pack();
    }
}
