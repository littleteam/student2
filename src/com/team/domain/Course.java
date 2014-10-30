package com.team.domain;

/**
 * Created by 斌 on 2014/10/26.
 */
public class Course {
    private int couId;

    public int getCouId() {
        return couId;
    }

    public void setCouId(int couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public Integer getCouGrade() {
        return couGrade;
    }

    public void setCouGrade(Integer couGrade) {
        this.couGrade = couGrade;
    }

    private String couName;
    private Integer couGrade;
    private String couSchName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (couId != course.couId) return false;
        if (couGrade != null ? !couGrade.equals(course.couGrade) : course.couGrade != null) return false;
        if (couName != null ? !couName.equals(course.couName) : course.couName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = couId;
        result = 31 * result + (couName != null ? couName.hashCode() : 0);
        result = 31 * result + (couGrade != null ? couGrade.hashCode() : 0);
        return result;
    }

    public String getCouSchName() {
        return couSchName;
    }

    public void setCouSchName(String couSchName) {
        this.couSchName = couSchName;
    }
}
