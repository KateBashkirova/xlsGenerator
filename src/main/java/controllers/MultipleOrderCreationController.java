package controllers;

import POJO.MultipleOrder;
import POJO.MultipleOrderAdditional.ClientInfo;
import POJO.MultipleOrderAdditional.CustomerAddress;
import POJO.MultipleOrderAdditional.OrderContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MultipleOrderCreationController {

    @GetMapping(value = "/createMultipleOrder")
    public ModelAndView showNewOrderForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createMultipleOrder");
        return modelAndView;
    }

    @PostMapping(value = "/createMultipleOrder", consumes = "application/json")
    public void createOrder(@RequestBody MultipleOrder multipleOrder) {
        List<OrderContent> orderContent = multipleOrder.getContentList();
        List<ClientInfo> clientInfo = multipleOrder.getClientInfoList();
        List<CustomerAddress> customerAddress = multipleOrder.getAddressList();
        System.out.println(orderContent.get(0).getProductName());
    }
}
