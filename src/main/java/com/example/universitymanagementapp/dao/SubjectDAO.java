package com.example.universitymanagementapp.dao;

import com.example.universitymanagementapp.model.Subject;

import java.util.List;
import java.util.ArrayList;

public class SubjectDAO {
    private static List<Subject> subjects = new ArrayList<>();

    //add subject
    public void addSubject(Subject subject){
        subjects.add(subject);
    }

    //remove subject
    public void removeSubject(String subjectName){
        for (Subject subject : subjects){
            if (subject.getSubjectName().equalsIgnoreCase(subjectName)){
                subjects.remove(subject);
            }
        }
    }

    //edit/update subject
    public void updateSubject(Subject updatedSubject){
        for (Subject subject : subjects){
            if (updatedSubject.getSubjectName().equalsIgnoreCase(subject.getSubjectName())){
                subject.setSubjectCode(updatedSubject.getSubjectCode());
            }
        }
    }

    //search subject by name
    public Subject getSubjectByName(String subjectName){
        for (Subject subject : subjects){
            if (subject.getSubjectName().equalsIgnoreCase(subjectName)){
                return subject;
            }
        }
        return null;
    }

    //get all subjects
    public List<Subject> getAllSubjects(){
        return subjects;
    }



}
