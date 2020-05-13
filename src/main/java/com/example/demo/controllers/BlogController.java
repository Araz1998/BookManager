package com.example.demo.controllers;


import com.example.demo.modeles.Orders;
import com.example.demo.modeles.Plan;
import com.example.demo.repo.OrderRepository;
import com.example.demo.repo.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.Order;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    //переменная, которая обращаеться к интерфесу заказов, что бі вівести на страницу
    @Autowired

    private OrderRepository orderRepository;
    private PlanRepository planRepository;


    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String OrderPostAdd(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String full_order,@RequestParam String country,@RequestParam String adress,@RequestParam String zip, Model model){
        Orders orders = new Orders(firstName, lastName,full_order,country, adress, zip );
        orderRepository.save(orders);
        return "redirect:/blog";
    }


    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        Optional<Orders> ord = orderRepository.findById(id);
        ArrayList<Orders> res = new ArrayList<>();
        ord.ifPresent(res::add);
        model.addAttribute("ord", res);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!orderRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Orders> ord = orderRepository.findById(id);
        ArrayList<Orders> res = new ArrayList<>();
        ord.ifPresent(res::add);
        model.addAttribute("ord", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String OrderPostUpdate(@PathVariable(value = "id") long id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String full_order,@RequestParam String country,@RequestParam String adress,@RequestParam String zip, Model model){
        Orders orders = orderRepository.findById(id).orElseThrow(IllegalAccessError::new);
        orders.setFirstName(firstName);
        orders.setLastName(lastName);
        orders.setFull_order(full_order);
        orders.setCountry(country);
        orders.setAdress(adress);
        orders.setZip(zip);
        orderRepository.save(orders);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String OrderPostDelite(@PathVariable(value = "id") long id, Model model){
        Orders orders = orderRepository.findById(id).orElseThrow(IllegalAccessError::new);
        orderRepository.delete(orders);

        return "redirect:/blog";
    }
}
