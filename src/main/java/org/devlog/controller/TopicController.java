package org.devlog.controller;

import org.devlog.model.StudyTopic;
import org.devlog.model.enums.StudyStatus;
import org.devlog.model.enums.TopicCategory;
import org.devlog.service.StudyTopicService;
import org.devlog.ui.navigation.NotesOpener;
import org.devlog.ui.panels.TopicPanel;
import org.devlog.ui.table.StudyTopicTableModel;

import javax.swing.*;
import java.awt.*;

public class TopicController {
    private final StudyTopicService studyTopicService;
    private final TopicPanel topicPanel;
    private final StudyTopicTableModel studyTopicTableModel = new StudyTopicTableModel();
    private final NotesOpener notesOpener;

    public TopicController(StudyTopicService studyTopicService,
                           TopicPanel topicPanel,
                           NotesOpener notesOpener
    ) {
        this.studyTopicService = studyTopicService;
        this.topicPanel = topicPanel;
        this.notesOpener = notesOpener;

        init();
    }

    private void init() {
        initComboBoxes();
        initListeners();
        topicPanel.getTopicListTable().setModel(studyTopicTableModel);
    }

    private void initListeners() {

        topicPanel.getCreateButton().addActionListener(e -> addTopic());
        topicPanel.getDeleteButton().addActionListener(e->deleteSelectedTopic());
        topicPanel.getEditButton().addActionListener(e->editSelectedTopic());
    }

    private void addTopic() {
        String title = topicPanel.getTitleField().getText();
        if (title == null || title.isEmpty()) {
            return;
        }
        title = title.trim();
        TopicCategory category =
                (TopicCategory) topicPanel.getCategoryBox().getSelectedItem();
        StudyStatus status =
                (StudyStatus) topicPanel.getStatusBox().getSelectedItem();
        StudyTopic studyTopic = new StudyTopic(
                title,
                category,
                status,
                "",
                0
        );
        studyTopicService.addTopic(studyTopic);
        studyTopicTableModel.setStudyTopics(studyTopicService.getAllTopics());
        System.out.println(
                studyTopicService.getAllTopics());
        topicPanel.getTitleField().setText("");

    }

    private void deleteSelectedTopic() {
        int selectedRow = topicPanel.getTopicListTable().getSelectedRow();

        if (selectedRow < 0) {
            return;
        }
        StudyTopic selectedTopic =
                studyTopicTableModel.getTopicAt(selectedRow);
        studyTopicService.deleteTopic(selectedTopic.getId());
        studyTopicTableModel.setStudyTopics(studyTopicService.getAllTopics());

    }
    private void editSelectedTopic() {
        int selectedRow = topicPanel.getTopicListTable().getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        StudyTopic selectedTopic =
                studyTopicTableModel.getTopicAt(selectedRow);
//        String newTitle = JOptionPane.showInputDialog("Enter new title");
//        if (newTitle == null || newTitle.isBlank()) {
//            return;
//        }
//        selectedTopic.setTitle(newTitle);
//        studyTopicTableModel.setStudyTopics(studyTopicService.getAllTopics());
        JTextField titleField = new JTextField(selectedTopic.getTitle());
        JComboBox <TopicCategory> categoryBox = new JComboBox<>(TopicCategory.values());
        categoryBox.setSelectedItem(selectedTopic.getCategory());
        JComboBox <StudyStatus> statusBox = new JComboBox<>(StudyStatus.values());
        statusBox.setSelectedItem(selectedTopic.getStatus());
        JTextField hoursField = new JTextField(String.valueOf(selectedTopic.getHoursSpent()));
        JButton openNotesEditor = new JButton("Open notes editor");

        JPanel editPanel = new JPanel(new GridLayout(0 , 2 , 5 ,5));
        editPanel.add(new JLabel("Title:"));
        editPanel.add(titleField);
        editPanel.add(new JLabel("Category:"));
        editPanel.add(categoryBox);
        editPanel.add(new JLabel("Status:"));
        editPanel.add(statusBox);
        editPanel.add(new JLabel("Hours:"));
        editPanel.add(hoursField);
        editPanel.add(new JLabel("Notes"));
        editPanel.add(openNotesEditor);
        openNotesEditor.addActionListener(e -> {
            notesOpener.open(selectedTopic);
            Window window = SwingUtilities.getWindowAncestor(openNotesEditor);
            if (window != null) {
                window.dispose();
            }
        });
        int result = JOptionPane.showConfirmDialog(
                null,
                editPanel,
                "Edit Topic",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        selectedTopic.setTitle(titleField.getText());
        selectedTopic.setCategory((TopicCategory) categoryBox.getSelectedItem());
        selectedTopic.setStatus((StudyStatus) statusBox.getSelectedItem());
        double hoursSpent;
        try{
            String hoursText = hoursField.getText().replace(",", ".");
            hoursSpent = Double.parseDouble(hoursText);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Hours must be a number!"
            );
            return;
        }
        selectedTopic.setHoursSpent(hoursSpent);
        studyTopicTableModel.setStudyTopics(
                studyTopicService.getAllTopics()
        );



    }


    private void initComboBoxes() {
        for(TopicCategory category : TopicCategory.values()) {
            topicPanel.getCategoryBox().addItem(category);
        }
        for(StudyStatus status : StudyStatus.values()) {
            topicPanel.getStatusBox().addItem(status);
        }

    }

}
