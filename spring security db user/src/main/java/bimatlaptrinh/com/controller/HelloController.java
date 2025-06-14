package bimatlaptrinh.com.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "🌐 Xin chào! Đây là trang công khai.";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "👤 Hello USER hoặc ADMIN!";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "🔐 Xin chào ADMIN quyền lực!";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/secure")
    public String adminOnlyMethod() {
        return "🔒 Đây là API riêng cho ADMIN – kiểm tra bằng @PreAuthorize";
    }
}