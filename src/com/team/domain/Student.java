package com.team.domain;

/**
 * Created by 斌 on 2014/10/26.
 */
public class Student {
    private int stuId;
    private String stuName;
    private String stuSex;
    private Integer stuGrade;
    private Integer schId;
    private String schName;

    public void setStuGrade(int stuGrade) {
        this.stuGrade = stuGrade;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Integer stuGrade) {
        this.stuGrade = stuGrade;
    }

    public Integer getSchId() {
        return schId;
    }

    public void setSchId(Integer schId) {
        this.schId = schId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (stuId != student.stuId) return false;
        if (schId != null ? !schId.equals(student.schId) : student.schId != null) return false;
        if (stuGrade != null ? !stuGrade.equals(student.stuGrade) : student.stuGrade != null) return false;
        if (stuName != null ? !stuName.equals(student.stuName) : student.stuName != null) return false;
        if (stuSex != null ? !stuSex.equals(student.stuSex) : student.stuSex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId;
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        result = 31 * result + (stuGrade != null ? stuGrade.hashCode() : 0);
        result = 31 * result + (schId != null ? schId.hashCode() : 0);
        return result;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }
}
