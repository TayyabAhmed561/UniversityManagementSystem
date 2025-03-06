package com.universitymanagement.service;

import com.universitymanagement.model.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectService {

    // Simulating a subject list (this could be replaced by a database in the future)
    private final List<Subject> subjects = new ArrayList<>();

    // Method to get all subjects as a list
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects);
    }

    // Method to add a new subject (returns true if added, false if duplicate code)
    public boolean addSubject(String subjectCode, String subjectName) {
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equalsIgnoreCase(subjectCode)) {
                return false; // Duplicate code found
            }
        }
        subjects.add(new Subject(subjectCode, subjectName));
        return true;
    }

    // Method to delete a subject by its code
    public boolean deleteSubject(String subjectCode) {
        return subjects.removeIf(subject -> subject.getSubjectCode().equalsIgnoreCase(subjectCode));
    }

    // Method to search subjects by keyword (subject code or name)
    public List<Subject> searchSubjects(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String lowerCaseKeyword = keyword.toLowerCase();

        return subjects.stream()
                .filter(subject -> subject.getSubjectCode().toLowerCase().contains(lowerCaseKeyword)
                        || subject.getSubjectName().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
    }
}
