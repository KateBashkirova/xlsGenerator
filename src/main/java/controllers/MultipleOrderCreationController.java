package controllers;

import POJO.MultipleOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println(multipleOrder.getOrderID());
    }
}
