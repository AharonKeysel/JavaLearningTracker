package org.devlog.model;

import org.devlog.model.enums.StudyStatus;
import org.devlog.model.enums.TopicCategory;

import java.time.LocalDate;
import java.util.UUID;

public class StudyTopic {
    private final UUID id;
    private String title;
    private TopicCategory category;
    private StudyStatus status;
    private double hoursSpent;
    private final LocalDate createdAt;
    private String notes;

    public StudyTopic(String title,TopicCategory category, StudyStatus status, String notes, double hoursSpent) {
        this.title = title;
        this.category = category;
        this.status = status;
        this.notes = notes;
        this.id = UUID.randomUUID();
        this.hoursSpent = hoursSpent;
        this.createdAt = LocalDate.now();
    }

    public TopicCategory getCategory() {
        return category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public double getHoursSpent() {
        return hoursSpent;
    }

    public UUID getId() {
        return id;
    }

    public String getNotes() {
        return notes;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(TopicCategory category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setHoursSpent(double hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    @Override
    public String toString() {
        return "StudyTopic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", hoursSpent=" + hoursSpent +
                ", createdAt=" + createdAt +
                ", notes='" + notes + '\'' +
                '}';

    }
}
