package org.devlog;

import org.devlog.model.StudyTopic;
import org.devlog.model.enums.StudyStatus;
import org.devlog.model.enums.TopicCategory;
import org.devlog.repository.StudyTopicRepository;
import org.devlog.repository.impl.InMemoryStudyTopicRepository;
import org.devlog.service.StudyTopicService;
import org.devlog.service.impl.StudyTopicServiceImpl;
import org.devlog.ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}