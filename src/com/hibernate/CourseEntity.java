package com.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "course_registertration", catalog = "")
public class CourseEntity {
    private int id;
    private String semesterName;
    private String year;
    private String courseId;
    private String coursetName;
    private int credits;
    private String teacherName;
    private String roomName;
    private String dayOfWeek;
    private String timeOfDay;
    private int slotMax;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "semesterName", nullable = false, length = 20)
    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    @Basic
    @Column(name = "year", nullable = false)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "courseID", nullable = false, length = 20)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "coursetName", nullable = false, length = 50)
    public String getCoursetName() {
        return coursetName;
    }

    public void setCoursetName(String coursetName) {
        this.coursetName = coursetName;
    }

    @Basic
    @Column(name = "credits", nullable = false)
    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
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
        return id == that.id && Objects.equals(year, that.year) && Objects.equals(semesterName, that.semesterName) && credits == that.credits && slotMax == that.slotMax && Objects.equals(courseId, that.courseId) && Objects.equals(coursetName, that.coursetName) && Objects.equals(teacherName, that.teacherName) && Objects.equals(roomName, that.roomName) && Objects.equals(dayOfWeek, that.dayOfWeek) && Objects.equals(timeOfDay, that.timeOfDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semesterName, year, courseId, coursetName, credits, teacherName, roomName, dayOfWeek, timeOfDay, slotMax);
    }
}
