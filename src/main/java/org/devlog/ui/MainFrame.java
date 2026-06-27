package org.devlog.ui;

import org.devlog.controller.TopicController;
import org.devlog.repository.impl.InMemoryStudyTopicRepository;
import org.devlog.service.impl.StudyTopicServiceImpl;
import org.devlog.ui.panels.TopicPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {


    public MainFrame(){
        initComponents();
        configureFrame();
    }

    private void configureFrame() {
        setTitle("Java Learning Tracker");
        setSize(900, 600);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        TopicPanel topicPanel = new TopicPanel();
        new TopicController(
                new StudyTopicServiceImpl(
                        new InMemoryStudyTopicRepository()
                ),
                topicPanel
        );
        add(topicPanel.getMainPanel(), BorderLayout.CENTER);
    }

}
