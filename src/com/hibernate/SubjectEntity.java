package com.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "course_registertration", catalog = "")
public class SubjectEntity {
    private int id;
    private String subjectId;
    private String subjectName;
    private int credits;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "subjectID", nullable = false, length = 20)
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    @Basic
    @Column(name = "subjectName", nullable = false, length = 50)
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Basic
    @Column(name = "credits", nullable = false)
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return id == that.id && credits == that.credits && Objects.equals(subjectId, that.subjectId) && Objects.equals(subjectName, that.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectId, subjectName, credits);
    }
}
