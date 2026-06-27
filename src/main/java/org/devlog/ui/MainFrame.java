package org.devlog.ui;

import org.devlog.controller.NotesController;
import org.devlog.controller.TopicController;
import org.devlog.repository.impl.InMemoryStudyTopicRepository;
import org.devlog.service.StudyTopicService;
import org.devlog.service.impl.StudyTopicServiceImpl;
import org.devlog.ui.panels.NotesPanel;
import org.devlog.ui.panels.TopicPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private TopicPanel topicPanel;
    private NotesPanel notesPanel;


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
        topicPanel = new TopicPanel();
        notesPanel = new NotesPanel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Topics", topicPanel.getMainPanel());
        tabbedPane.addTab("Notes", notesPanel.getMainPanel());

        InMemoryStudyTopicRepository repository = new InMemoryStudyTopicRepository();
        StudyTopicService service = new StudyTopicServiceImpl(repository);
        NotesController notesController = new NotesController(service, notesPanel);
        new TopicController(
                service,
                topicPanel,
                topic -> {
                    notesController.openNotesForTopic(topic);
                    tabbedPane.setSelectedComponent(
                            notesPanel.getMainPanel()
                    );
                });
        tabbedPane.addChangeListener(e -> {
            if(tabbedPane.getSelectedComponent() == notesPanel.getMainPanel()){
                notesController.refreshTopics();
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }
}
