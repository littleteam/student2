package com.team.domain;

/**
 * Created by 斌 on 2014/10/26.
 */
public class School {
    private int schId;
    private String schName;

    public int getSchId() {
        return schId;
    }

    public void setSchId(int schId) {
        this.schId = schId;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (schId != school.schId) return false;
        if (schName != null ? !schName.equals(school.schName) : school.schName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schId;
        result = 31 * result + (schName != null ? schName.hashCode() : 0);
        return result;
    }
}
