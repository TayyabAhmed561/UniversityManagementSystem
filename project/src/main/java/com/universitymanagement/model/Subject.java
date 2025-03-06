package com.universitymanagement.model;

import java.util.Objects;

public class Subject {
    private String subjectCode;
    private String subjectName;

    public Subject(String subjectCode, String subjectName) {
        setSubjectCode(subjectCode);
        setSubjectName(subjectName);
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        if (isValidSubjectCode(subjectCode)) {
            this.subjectCode = subjectCode;
        } else {
            throw new IllegalArgumentException("Invalid subject code. It must be non-empty and follow the pattern (e.g., CS101).");
        }
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject name cannot be empty.");
        }
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return subjectCode + " - " + subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectCode, subject.subjectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectCode);
    }

    private boolean isValidSubjectCode(String code) {
        // Example validation pattern: Code must be non-empty and can follow a pattern like "CS101"
        return code != null && code.matches("^[A-Z]{2,4}\\d{3}$");
    }
}
