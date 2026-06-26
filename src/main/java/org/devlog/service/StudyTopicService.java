package org.devlog.service;

import org.devlog.model.StudyTopic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudyTopicService {
    void addTopic(StudyTopic topic);
    List<StudyTopic> getAllTopics();
    Optional<StudyTopic> getTopicById(UUID id);
    void deleteTopic(UUID id);
}
