package tech.csm.CustomerManagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.csm.CustomerManagement.dto.RegisterRequest;
import tech.csm.CustomerManagement.entity.User;
import tech.csm.CustomerManagement.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public User register(RegisterRequest req, String photoFileName) {
        User user = new User();
        user.setUserName(req.getUserName());
        user.setUserPhone(req.getUserPhone());
        user.setUserEmail(req.getUserEmail());
        user.setGender(req.getGender());
        user.setPhoto(photoFileName);
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("USER");
        return userRepo.save(user);
    }

    public List<User> getAllUsers() { return userRepo.findAll(); }

    public User findByUserName(String userName) {
        return userRepo.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
