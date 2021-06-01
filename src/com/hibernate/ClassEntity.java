package com.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "class", schema = "course_registertration", catalog = "")
public class ClassEntity {
    private int id;
    private String className;
    private int countStudent;
    private int countMen;
    private int countWomen;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "countStudent", nullable = false)
    public int getCountStudent() {
        return countStudent;
    }

    public void setCountStudent(int countStudent) {
        this.countStudent = countStudent;
    }

    @Basic
    @Column(name = "countMen", nullable = false)
    public int getCountMen() {
        return countMen;
    }

    public void setCountMen(int countMen) {
        this.countMen = countMen;
    }

    @Basic
    @Column(name = "countWomen", nullable = false)
    public int getCountWomen() {
        return countWomen;
    }

    public void setCountWomen(int countWomen) {
        this.countWomen = countWomen;
    }

    @Basic
    @Column(name = "className", nullable = false)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return id == that.id && countStudent == that.countStudent && countMen == that.countMen && countWomen == that.countWomen && className == that.className;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countStudent, countMen, countWomen,className);
    }
}
