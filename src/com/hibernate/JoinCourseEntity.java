package com.hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "joincourse", schema = "course_registertration", catalog = "")
public class JoinCourseEntity {
    private int id;
    private int id_course;
    private int id_student;
    private LocalDateTime dayRegistration;

    public LocalDateTime getDayRegistration() {
        return dayRegistration;
    }

    public void setDayRegistration(LocalDateTime dayRegistration) {
        this.dayRegistration = dayRegistration;
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
    @Column(name = "ID_student", nullable = false)
    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }

    @Basic
    @Column(name = "ID_course", nullable = false)
    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JoinCourseEntity that = (JoinCourseEntity) o;
        return id == that.id && id_student == that.id_student && id_course == that.id_course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_student,id_course);
    }
}
