package org.devlog.ui.navigation;

import org.devlog.model.StudyTopic;

@FunctionalInterface
public interface NotesOpener {
    void open(StudyTopic topic);
}
