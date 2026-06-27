package org.devlog.repository.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.devlog.model.StudyTopic;
import org.devlog.repository.StudyTopicRepository;
import org.devlog.util.LocalDateAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JsonStudyTopicRepository implements StudyTopicRepository {
    private final String filePath = "topics.json";
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    private List<StudyTopic> topics = new ArrayList<>();

    public JsonStudyTopicRepository() {
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            topics = new ArrayList<>();
            return;
        }
        try(FileReader fileReader = new FileReader(file)){
            Type listType = new TypeToken<List<StudyTopic>>(){}.getType();
            List<StudyTopic> loadedTopics = gson.fromJson(fileReader, listType);
            if(loadedTopics != null){
                topics = loadedTopics;
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        };

    }

    @Override
    public void save(StudyTopic topic) {
        topics.removeIf(t -> t.getId().equals(topic.getId()));
        topics.add(topic);
        saveToFile();

    }

    private void saveToFile() {
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(topics, fileWriter);
        }catch (IOException e){
            throw new RuntimeException("Failed to save topics to file",e);
        }
    }

    @Override
    public void delete(UUID id) {
        topics.removeIf(topic->topic.getId().equals(id));
        saveToFile();

    }

    @Override
    public List<StudyTopic> findAll() {
        return new ArrayList<>(topics);
    }

    @Override
    public Optional<StudyTopic> findById(UUID id) {
        return topics.stream()
                .filter(topic->topic.getId().equals(id))
                .findFirst();
    }
}
