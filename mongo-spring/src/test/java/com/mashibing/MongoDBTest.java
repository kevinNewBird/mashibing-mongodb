package com.mashibing;

import com.alibaba.fastjson.JSON;
import com.mashibing.mongodb.constant.*;
import com.mashibing.mongodb.entity.Bill;
import com.mashibing.mongodb.entity.Order;
import com.mashibing.mongodb.entity.OrderDetail;
import com.mashibing.mongodb.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * description  MongoDBTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:27  2021/8/4
 * company: TRS信息技术有限公司
 * version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoDBApplication.class)
@Slf4j
public class MongoDBTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        Order order = getOrderStr();
        Bill bill = new Bill();
        bill.setRemark("remark");
        bill.setContractNo("contractNo");
        order.setBill(bill);

        Order insert = mongoTemplate.insert(order);

        System.out.println(JSON.toJSONString(insert, true));
    }
    @Test
    public void update() {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is("黑龙江"));
        Update update = Update.update("shippingFee", 20);
        mongoTemplate.updateMulti(query, update, Order.class);
        System.out.println("修改成功");
    }
    @Test
    public void updateChild() {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is("天津").and("orderDetailList.sku").is("SKU32049587"));
        Update update = Update.update("orderDetailList.$.productName", "小米101");
        mongoTemplate.updateMulti(query, update, Order.class);
        System.out.println("修改成功");
    }

    @Test
    public void updateChild2() {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is("广西").and("bill.contractNo").is("contractNo"));
        Update update = Update.update("bill.$.remark", "remark1");
        mongoTemplate.updateMulti(query, update, Order.class);
        System.out.println("修改成功");
    }
    @Test
    public void delete() {
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is("黑龙江"));
        mongoTemplate.remove(query,Order.class);
        System.out.println("删除成功");
    }

    @Test
    public void findOne() {
        Query query = new Query();
        Order order = mongoTemplate.findOne(query, Order.class);
        log.info(JSON.toJSONString(order));
    }

    @Test
    public void findByCondition(){
        Query query = new Query();
        query.addCriteria(Criteria.where("province").is("海南"));
        List<Order> orders = mongoTemplate.find(query, Order.class);
        orders.forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }

    @Test
    public void findChildByCondition(){
        Criteria cri = Criteria.where("bill.contractNo").is("contractNo");
        Query query = new Query(cri);
        List<Order> orders = mongoTemplate.find(query, Order.class,"orders");
        orders.forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }


    public Order getOrderStr(){
        Order order = new Order();
        order.setProvince(AddressEnum.getRandomAddress());
        order.setShopName(ShopNameEnum.getRandomShopName());
        order.setPhone(NameAndPhone.getPhone());
        order.setOrderDate(DateGenerator.randomDate("2020-01-01","2020-11-31"));
        order.setStatus(OrderStatusEnum.getRandomStatus());
        order.setWaybillNo("JD" + NumberGenerator.generate());
        order.setShippingFee(10);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        int num = RandomUtil.getNum(1, 5);
        for (int i = 1; i <= num; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId((long) i);
            ProductEnum product = ProductEnum.getRandomProduct();
            orderDetail.setSku("SKU" + NumberGenerator.generate());
            orderDetail.setProductName(product.getValue());
            orderDetail.setQty(RandomUtil.getNum(1, 3));
            orderDetail.setPrice(product.getPrice());
            orderDetail.setCost((int) (product.getPrice() * 0.8));
            orderDetailList.add(orderDetail);
        }
        order.setTotal(RandomUtil.getNum(2000,10000));
        order.setOrderDetailList(orderDetailList);
        return order;
    }

}
