package org.lambdas.model;

import java.util.Objects;

public class Consumer {

    private String cid;
    private String pid;
    private Boolean knownYourCustomer;
    private Boolean isBlocked;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Boolean getKnownYourCustomer() {
        return knownYourCustomer;
    }

    public void setKnownYourCustomer(Boolean knownYourCustomer) {
        this.knownYourCustomer = knownYourCustomer;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer that = (Consumer) o;
        return Objects.equals(cid, that.cid) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(knownYourCustomer, that.knownYourCustomer) &&
                Objects.equals(isBlocked, that.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, pid, knownYourCustomer, isBlocked);
    }
}
