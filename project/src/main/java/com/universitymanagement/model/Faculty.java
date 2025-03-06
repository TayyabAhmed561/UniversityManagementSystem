
package com.universitymanagement.model;

import java.util.List;

// attributes
public class Faculty {
    private String name;
    private String email;
    private String degree;
    private String researchInterest;
    private List<String> coursesOffered;
    private String profilePhoto;
    private String officeLocation;

    // constructor
    public Faculty(String name, String email, String degree, String researchInterest,
                   List<String> coursesOffered, String profilePhoto, String officeLocation) {
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.researchInterest = researchInterest;
        this.coursesOffered = coursesOffered;
        this.profilePhoto = profilePhoto;
        this.officeLocation = officeLocation;
    }

    // getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", degree='" + degree + '\'' +
                ", researchInterest='" + researchInterest + '\'' +
                ", coursesOffered=" + coursesOffered +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", officeLocation='" + officeLocation + '\'' +
                '}';
    }

}
