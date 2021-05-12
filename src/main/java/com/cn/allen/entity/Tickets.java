package com.cn.allen.entity;

import java.util.Date;

public class Tickets {
    private Integer ticketid;

    private Integer ticketcount;

    private Integer version;

    private Date gmtCreate;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getTicketcount() {
        return ticketcount;
    }

    public void setTicketcount(Integer ticketcount) {
        this.ticketcount = ticketcount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketid=").append(ticketid);
        sb.append(", ticketcount=").append(ticketcount);
        sb.append(", version=").append(version);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append("]");
        return sb.toString();
    }
}