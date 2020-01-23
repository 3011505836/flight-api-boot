package com.fh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Flight {

    private Integer id;
    private String name;
    private Integer typeId; // 机型id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;// 起飞时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;// 到大时间

    private Integer startTerminalId;// 出发机场航站楼id
    private Integer endTerminalId;// 到大机场航站楼id

    private Integer ticketId;
    private BigDecimal ticketPrice;
    private Integer ticketCount;


    private String flightStartName;
    private String flightEndName;
    private String flightName;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStartTerminalId() {
        return startTerminalId;
    }

    public void setStartTerminalId(Integer startTerminalId) {
        this.startTerminalId = startTerminalId;
    }

    public Integer getEndTerminalId() {
        return endTerminalId;
    }

    public void setEndTerminalId(Integer endTerminalId) {
        this.endTerminalId = endTerminalId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getFlightStartName() {
        return flightStartName;
    }

    public void setFlightStartName(String flightStartName) {
        this.flightStartName = flightStartName;
    }

    public String getFlightEndName() {
        return flightEndName;
    }

    public void setFlightEndName(String flightEndName) {
        this.flightEndName = flightEndName;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }
}
