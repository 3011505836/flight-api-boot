package com.fh.mapper;

import com.fh.model.*;

import java.util.List;

public interface FlightMapper {

    List<Flight> queryFlightList(FlightQuery flightQuery);

    Long queryFlightCount(FlightQuery flightQuery);

    void addFlight(Flight flight);

    Flight getFlightById(Integer id);

    List<Type> queryTypeList();

    List<Area> queryAreaByPid(Integer pid);

    void batchAddFlightTicket(List<FlightTicket> flightTicketList);

    List<FlightTicket> queryTicketByFlightId(Integer id);
}
