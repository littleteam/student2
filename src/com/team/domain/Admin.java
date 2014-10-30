package com.team.domain;

/**
 * Created by 斌 on 2014/10/26.
 */
public class Admin {
    private int admId;
    private String admName;
    private String admSex;
    private String schName;

    public int getAdmId() {
        return admId;
    }

    public void setAdmId(int admId) {
        this.admId = admId;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public String getAdmSex() {
        return admSex;
    }

    public void setAdmSex(String admSex) {
        this.admSex = admSex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (admId != admin.admId) return false;
        if (admName != null ? !admName.equals(admin.admName) : admin.admName != null) return false;
        if (admSex != null ? !admSex.equals(admin.admSex) : admin.admSex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = admId;
        result = 31 * result + (admName != null ? admName.hashCode() : 0);
        result = 31 * result + (admSex != null ? admSex.hashCode() : 0);
        return result;
    }

    public String getSchName() {
        return schName;
    }

    public void setSchName(String schName) {
        this.schName = schName;
    }
}
