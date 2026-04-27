package tech.csm.CustomerManagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.csm.CustomerManagement.dto.LoginRequest;
import tech.csm.CustomerManagement.dto.LoginResponse;
import tech.csm.CustomerManagement.dto.RegisterRequest;
import tech.csm.CustomerManagement.entity.User;
import tech.csm.CustomerManagement.service.UserService;
import tech.csm.CustomerManagement.util.FileUtil;
import tech.csm.CustomerManagement.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final FileUtil fileUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestPart("data") RegisterRequest req,
            @RequestPart(value = "photo", required = false) MultipartFile photo) {

        String photoFileName = null;
        if (photo != null && !photo.isEmpty()) {
            photoFileName = fileUtil.uploadFile(photo);
        }
        return ResponseEntity.ok(userService.register(req, photoFileName));
    }

   /* @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        return ResponseEntity.ok(userService.register(req, null));
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword())
        );
        User user = userService.findByUserName(req.getUserName());
        String token = jwtUtil.generateToken(user.getUserName(), user.getRole());
        return ResponseEntity.ok(new LoginResponse(token, user.getRole()));
    }

    @GetMapping("/userdetails")
    public ResponseEntity<?> userDetails(Authentication auth) {
        return ResponseEntity.ok(userService.findByUserName(auth.getName()));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
