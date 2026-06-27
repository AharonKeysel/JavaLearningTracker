package org.devlog.ui.panels;

import org.devlog.model.StudyTopic;

import javax.swing.*;
import java.awt.*;

public class NotesPanel {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<StudyTopic> topicComboBox;
    private JTextArea notesArea;
    private JButton saveNotesButton;

    public NotesPanel() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLabel = new JLabel("Notes");

        topicComboBox = new JComboBox<>();
        topicComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus
            ) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof StudyTopic topic) {
                    setText(topic.getTitle());
                }

                return this;
            }
        });
        notesArea = new JTextArea();
        saveNotesButton = new JButton("Save");

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(topicComboBox, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(saveNotesButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(notesArea), BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox<StudyTopic> getTopicComboBox() {
        return topicComboBox;
    }

    public JTextArea getNotesArea() {
        return notesArea;
    }

    public JButton getSaveNotesButton() {
        return saveNotesButton;
    }

}
