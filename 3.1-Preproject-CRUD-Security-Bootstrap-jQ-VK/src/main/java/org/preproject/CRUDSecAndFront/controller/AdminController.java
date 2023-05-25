package org.preproject.CRUDSecAndFront.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String unionPage() {
        return "unionPage";
    }
}
