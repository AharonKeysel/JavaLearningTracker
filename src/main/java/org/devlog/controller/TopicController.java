package org.devlog.controller;

import org.devlog.model.StudyTopic;
import org.devlog.model.enums.StudyStatus;
import org.devlog.model.enums.TopicCategory;
import org.devlog.service.StudyTopicService;
import org.devlog.ui.panels.TopicPanel;
import org.devlog.ui.table.StudyTopicTableModel;

public class TopicController {
    private final StudyTopicService studyTopicService;
    private final TopicPanel topicPanel;
    private final StudyTopicTableModel studyTopicTableModel = new StudyTopicTableModel();

    public TopicController(StudyTopicService studyTopicService, TopicPanel topicPanel) {
        this.studyTopicService = studyTopicService;
        this.topicPanel = topicPanel;
        init();
    }
    private void init() {
        initComboBoxes();
        initListeners();
        topicPanel.getTopicListTable().setModel(studyTopicTableModel);
    }

    private void initListeners() {
        topicPanel.getCreateButton().addActionListener(e -> addTopic());
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

    private void initComboBoxes() {
        for(TopicCategory category : TopicCategory.values()) {
            topicPanel.getCategoryBox().addItem(category);
        }
        for(StudyStatus status : StudyStatus.values()) {
            topicPanel.getStatusBox().addItem(status);
        }

    }
}
