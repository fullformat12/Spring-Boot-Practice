package bimatlaptrinh.com.controller;

import lombok.RequiredArgsConstructor;
import bimatlaptrinh.com.dtos.AuthRequest;
import bimatlaptrinh.com.dtos.AuthResponse;
import bimatlaptrinh.com.dtos.RefreshRequest;
import bimatlaptrinh.com.model.Token;
import bimatlaptrinh.com.model.User;
import bimatlaptrinh.com.repository.TokenRepository;
import bimatlaptrinh.com.repository.UserRepository;
import bimatlaptrinh.com.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final TokenRepository tokenRepo;
    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.username(), req.password()));
        UserDetails user = (UserDetails) auth.getPrincipal();

        String access  = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        saveRefreshToken(user.getUsername(), refresh);
        return new AuthResponse(access, refresh);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest req) {
        String oldRefresh = req.refreshToken();
        Token saved = tokenRepo.findByToken(oldRefresh)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        if (saved.isRevoked() || jwtService.isExpired(oldRefresh, true))
            throw new RuntimeException("Refresh token expired or revoked");

        // Truy vết username rồi nạp đúng UserDetails
        String username = saved.getUser().getUsername();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // rotation: revoke old, issue new
        saved.setRevoked(true);
        tokenRepo.save(saved);

        String newAccess  = jwtService.generateAccessToken(userDetails);
        String newRefresh = jwtService.generateRefreshToken(userDetails);
        saveRefreshToken(userDetails.getUsername(), newRefresh);

        return new AuthResponse(newAccess, newRefresh);
    }

    private void saveRefreshToken(String username, String refresh) {
        User user = userRepo.findByUsername(username).orElseThrow();
        tokenRepo.save(Token.builder()
                .token(refresh)
                .expiryDate(Date.from(
                        Instant.now().plus(Duration.ofDays(7))))
                .revoked(false)
                .user(user)
                .build());
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        // Tùy bạn: đánh dấu revoke trong DB, hoặc xóa khỏi client
        return ResponseEntity.ok("Đã đăng xuất");
    }
}