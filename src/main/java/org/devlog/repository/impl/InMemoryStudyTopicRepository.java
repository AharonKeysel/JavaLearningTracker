package org.devlog.repository.impl;

import org.devlog.model.StudyTopic;
import org.devlog.repository.StudyTopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryStudyTopicRepository implements StudyTopicRepository {
    private final List<StudyTopic> topics = new ArrayList<>();


    @Override
    public void save(StudyTopic topic) {
        topics.add(topic);
    }

    @Override
    public void delete(UUID id) {
        topics.removeIf(topic->topic.getId().equals(id));

    }

    @Override
    public List<StudyTopic> findAll() {
        return topics;
    }

    @Override
    public Optional<StudyTopic> findById(UUID id) {
        return topics.stream()
                .filter(topic->topic.getId().equals(id))
                .findFirst();
    }

}
