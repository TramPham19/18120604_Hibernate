package com.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "course_registertration", catalog = "")
public class CourseEntity {
    private int id;
    private int credits;
    private String teacherName;
    private String roomName;
    private String dayOfWeek;
    private String timeOfDay;
    private int slotMax;
    private int idSemester;
    private int idSubject;



    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IdSemester", nullable = false, length = 20)
    public int getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(int idSemester) {
        this.idSemester = idSemester;
    }

    @Basic
    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    @Basic
    @Column(name = "teacherName", nullable = false, length = 50)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "roomName", nullable = false, length = 10)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "dayOfWeek", nullable = false, length = 20)
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Basic
    @Column(name = "timeOfDay", nullable = false, length = 50)
    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @Basic
    @Column(name = "slotMax", nullable = false)
    public int getSlotMax() {
        return slotMax;
    }

    public void setSlotMax(int slotMax) {
        this.slotMax = slotMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return id == that.id && idSemester == that.idSemester && idSubject == that.idSubject && credits == that.credits && slotMax == that.slotMax && Objects.equals(teacherName, that.teacherName) && Objects.equals(roomName, that.roomName) && Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(timeOfDay, that.timeOfDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idSemester, idSubject, credits, teacherName, roomName, dayOfWeek, timeOfDay, slotMax);
    }
}
