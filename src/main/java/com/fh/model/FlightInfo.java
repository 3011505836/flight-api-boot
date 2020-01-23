package com.fh.model;

import java.util.List;

public class FlightInfo {

    private Flight flight;

    private List<FlightTicket> flightTicketList;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<FlightTicket> getFlightTicketList() {
        return flightTicketList;
    }

    public void setFlightTicketList(List<FlightTicket> flightTicketList) {
        this.flightTicketList = flightTicketList;
    }
}
