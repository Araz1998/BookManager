package com.example.demo.controllers;

import com.example.demo.modeles.Orders;
import com.example.demo.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "about of us");
        return "about";
    }

    @GetMapping("/sign-in")
    public String Sign_in(Model model) {
        model.addAttribute("Sign-in", "Главная страница");
        return "sign-in";
    }


    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Orders> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "blog-main";
    }

}
