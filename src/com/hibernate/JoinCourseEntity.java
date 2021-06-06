package com.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "joincourse", schema = "course_registertration", catalog = "")
public class JoinCourseEntity {
    private int id;
    private String mssv;
    private int id_course;

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MSSV", nullable = false, length = 20)
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinCourseEntity that = (JoinCourseEntity) o;
        return id == that.id && Objects.equals(mssv, that.mssv) && id_course == that.id_course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mssv,id_course);
    }
}
