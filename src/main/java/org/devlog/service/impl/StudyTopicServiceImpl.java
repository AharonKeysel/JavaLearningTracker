package org.devlog.service.impl;

import org.devlog.model.StudyTopic;
import org.devlog.repository.StudyTopicRepository;
import org.devlog.service.StudyTopicService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudyTopicServiceImpl implements StudyTopicService {
    private final StudyTopicRepository studyTopicRepository;



    public StudyTopicServiceImpl(StudyTopicRepository studyTopicRepository) {
        this.studyTopicRepository = studyTopicRepository;
    }

    @Override
    public void addTopic(StudyTopic topic) {
        studyTopicRepository.save(topic);

    }

    @Override
    public List<StudyTopic> getAllTopics() {
        return studyTopicRepository.findAll();
    }

    @Override
    public Optional<StudyTopic> getTopicById(UUID id) {
        return studyTopicRepository.findById(id);
    }

    @Override
    public void deleteTopic(UUID id) {
        studyTopicRepository.delete(id);

    }
    @Override
    public void updateTopic(StudyTopic topic) {
        studyTopicRepository.save(topic);
    }
    @Override
    public List<StudyTopic> searchByTitle(String query) {
        if(query==null){
            return getAllTopics();
        }
        String normalizedQuery = query.strip().toLowerCase();
        if(normalizedQuery.isEmpty()){
            return getAllTopics();
        }
        return getAllTopics().stream()
                .filter(topic -> topic.getTitle()
                        .toLowerCase()
                        .contains(normalizedQuery))
                .toList();
    }
}
