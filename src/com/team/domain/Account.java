package com.team.domain;

/**
 * Created by 斌 on 2014/10/26.
 */
public class Account {
    private int accUid;
    private String accUname;
    private String accPass;
    private int accIsadmin;

    public int getAccUid() {
        return accUid;
    }

    public void setAccUid(int accUid) {
        this.accUid = accUid;
    }

    public String getAccUname() {
        return accUname;
    }

    public void setAccUname(String accUname) {
        this.accUname = accUname;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public int getAccIsadmin() {
        return accIsadmin;
    }

    public void setAccIsadmin(int accIsadmin) {
        this.accIsadmin = accIsadmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accIsadmin != account.accIsadmin) return false;
        if (accUid != account.accUid) return false;
        if (accPass != null ? !accPass.equals(account.accPass) : account.accPass != null) return false;
        if (accUname != null ? !accUname.equals(account.accUname) : account.accUname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accUid;
        result = 31 * result + (accUname != null ? accUname.hashCode() : 0);
        result = 31 * result + (accPass != null ? accPass.hashCode() : 0);
        result = 31 * result + accIsadmin;
        return result;
    }
}
