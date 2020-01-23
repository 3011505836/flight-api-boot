package com.fh.service.impl;

import com.fh.mapper.FlightMapper;
import com.fh.model.*;
import com.fh.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightMapper flightMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public DataTableResult queryFlightList(FlightQuery flightQuery) {

        //1.查询总条数
        Long count = flightMapper.queryFlightCount(flightQuery);

        //2.分页条件查询当前页数据
        List<Flight> flightList = flightMapper.queryFlightList(flightQuery);

        //3.构造DataTableResult对象
        DataTableResult dataTableResult = new DataTableResult(flightQuery.getDraw(), count, count, flightList);

        return dataTableResult;
    }

    @Override
    public void addFlight(FlightInfo flightInfo) {
        Flight flight = flightInfo.getFlight();
        flightMapper.addFlight(flight);
        List<FlightTicket> flightTicketList = flightInfo.getFlightTicketList();
        for(FlightTicket flightTicket : flightTicketList){
            flightTicket.setFlightId(flight.getId());
        }
        flightMapper.batchAddFlightTicket(flightTicketList);
    }

    @Override
    public Flight getFlightById(Integer id) {
        return flightMapper.getFlightById(id);
    }

    @Override
    public List<Type> queryTypeList() {
        return flightMapper.queryTypeList();
    }

    @Override
    public List<Area> queryAreaByPid(Integer pid) {
        return flightMapper.queryAreaByPid(pid);
    }

    @Override
    public List<FlightTicket> queryTicketByFlightId(Integer id) {
        return flightMapper.queryTicketByFlightId(id);
    }

    @Override
    public ServerResponse createOrder(OrderItem orderItem) {

        //判断商品id是否为空
        if(orderItem.getTicketId() == null){
            return ServerResponse.error(ResponseEnum.FLIGHT_IS_NULL);
        }

        String orderKey = "flight:" + orderItem.getUserId();
        String flightKey = orderItem.getTicketId() + "";
        //如果用户的购物车中不存在该商品，则向购物车中添加一个新的商品
        if(!redisTemplate.opsForHash().hasKey(orderKey, flightKey)){
            OrderItem orderItem1 = new OrderItem();
            orderItem1.setTicketId(orderItem.getTicketId());
            orderItem1.setUserId(orderItem.getUserId());
            orderItem1.setPrice(orderItem.getPrice());
            orderItem1.setRealName(orderItem.getRealName());
            redisTemplate.opsForHash().put(orderKey,flightKey,orderItem1);
        }
        return null;
    }


}
