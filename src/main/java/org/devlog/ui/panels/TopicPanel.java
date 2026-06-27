package org.devlog.ui.panels;

import javax.swing.*;

public class TopicPanel {
    private JPanel mainPanel;
    private JPanel topPanel;
    private JTextField titleField;
    private JPanel rightPanel;
    private JComboBox categoryBox;
    private JButton createButton;
    private JComboBox statusBox;
    private JScrollPane topicTableScrollPanel;

    public JTable getTopicListTable() {
        return topicListTable;
    }

    public JScrollPane getTopicTableScrollPanel() {
        return topicTableScrollPanel;
    }

    private JTable topicListTable;
    private JButton deleteButton;

    public JComboBox getCategoryBox() {
        return categoryBox;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JComboBox getStatusBox() {
        return statusBox;
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}
