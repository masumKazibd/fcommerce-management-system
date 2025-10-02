package com.fcmis.fcmis_API;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping({"/", "/dashboard"})
//    @ResponseBody // Add this annotation temporarily
//    public String showDashboard() {
//        return "Hello World! Controller is working.";
//    }
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/products")
    public String products() {
        return "products";
    }

    @GetMapping("/sales")
    public String sales() {
        return "sales";
    }

    @GetMapping("/expenses")
    public String expenses() {
        return "expenses";
    }
}
