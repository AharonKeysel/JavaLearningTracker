package org.devlog;

import org.devlog.model.StudyTopic;
import org.devlog.model.enums.StudyStatus;
import org.devlog.model.enums.TopicCategory;
import org.devlog.repository.StudyTopicRepository;
import org.devlog.repository.impl.InMemoryStudyTopicRepository;
import org.devlog.service.StudyTopicService;
import org.devlog.service.impl.StudyTopicServiceImpl;

public class Main {
    public static void main(String[] args) {

        StudyTopicRepository repository = new InMemoryStudyTopicRepository();

        StudyTopicService service = new StudyTopicServiceImpl(repository);

        StudyTopic streams = new StudyTopic(
                "Streams",
                TopicCategory.JAVA_CORE,
                StudyStatus.IN_PROGRESS,
                "Повторить filter, map, collect",
                3.5
        );

        StudyTopic lambdas = new StudyTopic(
                "Lambdas",
                TopicCategory.JAVA_CORE,
                StudyStatus.COMPLETED,
                "В целом понятно",
                2.0
        );

        service.addTopic(streams);
        service.addTopic(lambdas);

        System.out.println("Все темы:");
        service.getAllTopics()
                .forEach(System.out::println);

        System.out.println("\nПоиск по ID:");
        service.getTopicById(streams.getId())
                .ifPresent(System.out::println);

        System.out.println("\nУдаляем Lambdas...");
        service.deleteTopic(lambdas.getId());

        System.out.println("\nПосле удаления:");
        service.getAllTopics()
                .forEach(System.out::println);
    }
    //FirstMVP_Test

}