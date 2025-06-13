package bimatlaptrinh.com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Chào mừng bạn đến với API công khai!";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "Xin chào USER hoặc ADMIN!";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Xin chào ADMIN quyền lực!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/secure")
    public String adminSecure() {
        return "Chỉ ADMIN mới được thấy dòng này!";
    }
}
