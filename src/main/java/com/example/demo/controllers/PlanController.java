package com.example.demo.controllers;

import com.example.demo.modeles.Plan;
import com.example.demo.repo.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PlanController {

    @Autowired
    private PlanRepository planRepository;


    @GetMapping("/")
    public  String planMain(Model model){
        Iterable<Plan> plan = planRepository.findAll();
        model.addAttribute("plans", plan);
        return "home";
    }

    @GetMapping("/plan")
    public String planAdd(Model model){
        return "plan-add";
    }



    @PostMapping("/plan")
    public String PlanPostAdd(@RequestParam String name, @RequestParam String planned, Model model){
        Plan plans = new Plan(name, planned);
        planRepository.save(plans);
        return "plan-add";
    }

    @GetMapping("/{id}")
    public String planDetails(@PathVariable(value = "id") long id, Model model){
        Optional<Plan> plan = planRepository.findById(id);
        ArrayList<Plan> res = new ArrayList<>();
        plan.ifPresent(res::add);
        model.addAttribute("plan", res);
        return "redirect:/home";
    }

    @PostMapping("/{id}/remove")
    public String PlanPostDelite(@PathVariable(value = "id") long id, Model model){
        Plan plan = planRepository.findById(id).orElseThrow(IllegalAccessError::new);
        planRepository.delete(plan);
        return "home";
    }

}
