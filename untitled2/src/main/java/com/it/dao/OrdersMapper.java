package com.it.dao;

import com.it.pojo.Orders;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    // 根据订单id查询订单信息以及关联的商品信息
    Orders findOrderWithProducts(@Param("orderId") int orderId);
}