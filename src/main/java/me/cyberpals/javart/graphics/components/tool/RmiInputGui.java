package me.cyberpals.javart.graphics.components.tool;

import javax.swing.*;

public class RmiInputGui {
    int port;
    String ip;

    //for ui purposes
    JTextField ipField;
    JSpinner portField;

    JPanel rmiAsk;

    Icon info, error;

    Boolean cancelled = false;

    public RmiInputGui(Icon info, Icon error) {
        this.port = 1099;
        this.ip = "localhost";

        this.info = info;
        this.error = error;

        this.ipField = new JTextField(this.ip);
        this.portField = new JSpinner(new SpinnerNumberModel(this.port, 0, 65535, 1));

        this.rmiAsk = new JPanel();
        this.rmiAsk.add(new JLabel("ip:"));
        this.rmiAsk.add(ipField);
        this.rmiAsk.add(Box.createHorizontalStrut(15));
        this.rmiAsk.add(new JLabel("port:"));
        this.rmiAsk.add(portField);
    }

    public void ask() {
        int result = JOptionPane.showConfirmDialog(null, rmiAsk, "RMI Connection", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, info);
        if (result == JOptionPane.OK_OPTION) {
            this.ip = ipField.getText();
            this.port = (int) portField.getValue();
            cancelled = false;
        } else {
            JOptionPane.showMessageDialog(null, "RMI connection cancelled", "RMI Connection", JOptionPane.ERROR_MESSAGE, error);
            cancelled = true;
        }
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public Boolean isCancelled() {
        return cancelled;
    }
}
