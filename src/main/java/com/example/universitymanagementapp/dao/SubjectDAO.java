package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Subject;

import java.util.List;
import java.util.ArrayList;

public class SubjectDAO {

    private List<Subject> subjects = new ArrayList();

    public SubjectDAO() {};

    //display all subjects
    public List<Subject> getSubjects() {
        return subjects;
    }

    //search subject by code
    public List<Subject> getSubjectByCode(String code) {
        if (code == null) {
            System.out.println("Subject code is invalid");
        }
        List<Subject> subjects = getSubjects();
        List<Subject> matchingSubjects = new ArrayList();
        for (Subject subject : subjects) {
            if (subject.getSubjectCode().equals(code)) {
                matchingSubjects.add(subject);
            }
            if (matchingSubjects.isEmpty()) {
                return null;
            }
        }
        return matchingSubjects;
    }

    //search subject by name
    public List<Subject> getSubjectByName(String name) {
        if (name == null) {
            System.out.println("Subject name is invalid");
        }
        List<Subject> subjects = getSubjects();
        List<Subject> matchingSubjects = new ArrayList();
        for (Subject subject : subjects) {
            if (subject.getSubjectName().equals(name)) {
                matchingSubjects.add(subject);
            }
        }
        return matchingSubjects;
    }

    //add subject
    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    //remove subject
    public void removeSubject(Subject subject){
        subjects.remove(subject);
    }

    //edit subject

}
