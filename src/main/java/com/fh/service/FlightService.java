package com.fh.service;

import com.fh.model.*;

import java.util.List;

public interface FlightService {
    DataTableResult queryFlightList(FlightQuery flightQuery);

    void addFlight(FlightInfo flightInfo);

    Flight getFlightById(Integer id);

    List<Type> queryTypeList();

    List<Area> queryAreaByPid(Integer pid);

    List<FlightTicket> queryTicketByFlightId(Integer id);

    ServerResponse createOrder(OrderItem orderItem);
}
