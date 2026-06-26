package org.devlog.repository;

import org.devlog.model.StudyTopic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudyTopicRepository {

    void save(StudyTopic topic);
    void delete(UUID id);
    List<StudyTopic> findAll();
    Optional<StudyTopic> findById(UUID id); // Optional хранит либо объект, либо его отсутствие.-> Позволяет избежать возврата null.

}
