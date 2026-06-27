package org.devlog.ui.table;

import org.devlog.model.StudyTopic;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudyTopicTableModel extends AbstractTableModel {
    private final String[] columns = {
            "Title",
            "Category",
            "Status",
            "Hours",
            "Created",
    };
    private final  List<StudyTopic> studyTopics = new ArrayList<>();

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public int getRowCount() {
        return studyTopics.size();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StudyTopic studyTopic = studyTopics.get(rowIndex);
        switch (columnIndex) {
            case 0: return studyTopic.getTitle();
            case 1: return studyTopic.getCategory();
            case 2: return studyTopic.getStatus();
            case 3: return studyTopic.getHoursSpent();
            case 4: return studyTopic.getCreatedAt();
            default: return null;
        }
    }
    public String getColumnName(int column) {
        return columns[column];
    }
    public void setStudyTopics(List<StudyTopic> studyTopics) {
        this.studyTopics.clear();
        this.studyTopics.addAll(studyTopics);
        fireTableDataChanged();
    }

}
