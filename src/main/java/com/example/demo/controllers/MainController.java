package com.example.demo.controllers;

import com.example.demo.modeles.Message;
import com.example.demo.modeles.Orders;
import com.example.demo.modeles.User;
import com.example.demo.repo.MessageRepo;
import com.example.demo.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }


    @GetMapping("/about")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
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

    @PostMapping("/about")
    public String add(@RequestParam("file") MultipartFile file,
                      @AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag, Model model) throws IOException {
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "about";
    }

}
