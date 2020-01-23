package com.fh.controller;

import com.fh.model.*;
import com.fh.service.FlightService;
import com.fh.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("FlightController")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @RequestMapping("queryAreaByPid")
    public ServerResponse queryAreaByPid(Integer pid){
        List<Area> areaList = flightService.queryAreaByPid(pid);
        return ServerResponse.success(areaList);
    }


    @RequestMapping("queryTypeList")
    public ServerResponse queryTypeList() {
        List<Type> typeList = flightService.queryTypeList();
        return ServerResponse.success(typeList);
    }

    //分页条件查询信息
    @RequestMapping("queryFlightList")
    public ServerResponse queryFlightList(FlightQuery flightQuery) {
        DataTableResult dataTableResult = flightService.queryFlightList(flightQuery);
        return ServerResponse.success(dataTableResult);
    }

    //新增
    @RequestMapping("addFlight")
    public ServerResponse addFlight(@RequestBody FlightInfo flightInfo) {
        flightService.addFlight(flightInfo);
        return ServerResponse.success();
    }

    //通过id获取单个信息
    @RequestMapping("getFlightById")
    public ServerResponse getFlightById(Integer id) {
        Flight flight = flightService.getFlightById(id);
        return ServerResponse.success(flight);
    }


    @RequestMapping("queryTicketByFlightId")
    public ServerResponse queryTicketByFlightId(Integer id) {
        List<FlightTicket> flightTicketList = flightService.queryTicketByFlightId(id);
        return ServerResponse.success(flightTicketList);
    }

    @RequestMapping("createOrder")
    public ServerResponse createOrder(HttpServletRequest request,OrderItem orderItem){
        try {
            User loginUser = (User) request.getAttribute("loginUser");
            orderItem.setUserId(loginUser.getId());
            ServerResponse serverResponse = flightService.createOrder(orderItem);
            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.error();
        }
    }

}
