package org.devlog.controller;

import org.devlog.model.StudyTopic;
import org.devlog.service.StudyTopicService;
import org.devlog.ui.panels.NotesPanel;

import javax.swing.*;

public class NotesController {
    private final StudyTopicService studyTopicService;
    private final NotesPanel notesPanel;

    public NotesController(StudyTopicService studyTopicService, NotesPanel notesPanel) {
        this.studyTopicService = studyTopicService;
        this.notesPanel = notesPanel;
        loadTopicsToComboBox();
        registerListeners();
    }

    private void registerListeners() {
        notesPanel.getTopicComboBox().addActionListener(e -> {
            StudyTopic selectedTopic = getSelectedTopic();
            if (selectedTopic == null) {
                return;
            }
            notesPanel.getNotesArea().setText(selectedTopic.getNotes());
        });

        notesPanel.getSaveNotesButton().addActionListener(e -> {
            StudyTopic selectedTopic = getSelectedTopic();
            if (selectedTopic == null) {
                return;
            }
            String newNotes = notesPanel.getNotesArea().getText();
            selectedTopic.setNotes(newNotes);
            studyTopicService.updateTopic(selectedTopic);
            JOptionPane.showMessageDialog(notesPanel.getNotesArea(), "Notes updated successfully!");
        });

    }

    private void loadTopicsToComboBox(){
        notesPanel.getTopicComboBox().removeAllItems();
        for(StudyTopic topic: studyTopicService.getAllTopics()){
            notesPanel.getTopicComboBox().addItem(topic);
        }
    }
    public void refreshTopics(){
        loadTopicsToComboBox();
    }
    private StudyTopic getSelectedTopic() {
        return (StudyTopic) notesPanel.getTopicComboBox().getSelectedItem();
    }

    public void openNotesForTopic(StudyTopic topic){
        refreshTopics();
        notesPanel.getTopicComboBox().setSelectedItem(topic);
        notesPanel.getNotesArea().setText(topic.getNotes());
    }
}
