package bimatlaptrinh.com.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class HelloController {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin-only")
    public String adminEndpoint() {
        return "Only admin can access this!";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/common")
    public String userAndAdmin() {
        return "Both user and admin can access this!";
    }
}