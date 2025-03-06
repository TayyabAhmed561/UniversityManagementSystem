package com.universitymanagement.controller.SubjectController;

import com.universitymanagement.model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubjectManager {
    private static final ObservableList<Subject> subjects = FXCollections.observableArrayList();

    static {
        subjects.addAll(
                new Subject("MATH101", "Calculus I"),
                new Subject("ENG102", "Literature Basics"),
                new Subject("CS103", "Introduction to Programming"),
                new Subject("CHEM104", "Introduction to Chemistry")
        );
    }

    public static ObservableList<Subject> getSubjects() {
        return subjects;
    }

    public static boolean addSubject(String code, String name) {
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equalsIgnoreCase(code)) {
                return false; // Subject code already exists
            }
        }
        subjects.add(new Subject(code, name));
        return true;
    }

    public static void editSubject(String oldCode, String newCode, String newName) {
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equals(oldCode)) {
                subject.setSubjectCode(newCode);
                subject.setSubjectName(newName);
                break;
            }
        }
    }

    public static void removeSubject(String code) {
        subjects.removeIf(subject -> subject.getSubjectCode().equals(code));
    }
}
