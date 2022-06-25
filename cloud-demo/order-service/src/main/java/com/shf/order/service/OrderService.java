package com.shf.order.service;

import com.shf.order.mapper.OrderMapper;
import com.shf.order.pojo.Order;
import com.shf.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 注册RestTemplate
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 服务远调用RestTemplate
     * @param orderId
     * @return
     */
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
//        2.利用RestTemplate发起http请求，查询用户
//        2.1 url路径
        String url = "http://localhost:8081/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
//        3.封装User到Order
        order.setUser(user);
        // 4.返回
        return order;
    }
}
