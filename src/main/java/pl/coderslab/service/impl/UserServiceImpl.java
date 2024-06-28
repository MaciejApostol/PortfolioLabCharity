package pl.coderslab.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.*;
import pl.coderslab.mapper.CustomerMapper;
import pl.coderslab.repository.RoleRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.DonationService;
import pl.coderslab.service.EmailService;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DonationService donationService;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           DonationService donationService, EmailService emailService,
                           BCryptPasswordEncoder passwordEncoder, CustomerMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.donationService = donationService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findByRole(String roleName) {
        return userRepository.findAllByRoles(roleName);
    }

    public User addRole(String roleName, User user) {
        Role role = roleRepository.findByName(roleName);
        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return user;
    }

    @Override
    public void addAdmin(User user) {
        user = addRole("ROLE_ADMIN", user);
        update(user);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(UserAvailability.ENABLED);
        user.setDeleted(Deleted.AVAILABLE);
        user = addRole("ROLE_USER", user);
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        User existingUser = findById(user.getId());
        mapper.updateUser(user, existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public void deleteById(CurrentUser currentUser, Long id) {
        User user = findById(id);
        if (currentUser.getUser().getId().equals(user.getId())) {
            return;
        }
        user.setEnabled(UserAvailability.ENABLED);
        user.setDeleted(Deleted.DELETED);
        userRepository.save(user);
    }

    @Override
    public void enableUser(Long id, UserAvailability enabled) {
        User user = findById(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    @Override
    public void deleteAdmin(CurrentUser currentUser, Long id) {
        User user = findById(id);
        if (currentUser.getUser().getId().equals(user.getId())) {
            return;
        }
        user = addRole("ROLE_USER", user);
        userRepository.save(user);
    }

    @Override
    public String processLoginForm(boolean error, User user, Model model) {
        model.addAttribute("user", user);
        if (!error) {
            User exisitingUser = findByEmail(user.getEmail());
            UserAvailability availability = exisitingUser.getEnabled();
            if (availability == UserAvailability.ENABLED) {
                return "redirect:welcome-page";
            }
            model.addAttribute("enabled", availability);
        }
        return "login";
    }

    @Override
    public void showWelcomePage(CurrentUser currentUser, Model model) {

    }

    @Override
    public void generateAndEmailToken(User user, HttpServletRequest request) {
        String email = user.getEmail();
        User existingUser = findByEmail(email);
        String token = UUID.randomUUID().toString();
        String requestURL = request.getRequestURL().toString().replace(request.getRequestURI(),
                String.format("/email-authentication?token=%s", token));
        emailService.sendEmail(email,
                "Odzyskiwanie hasła",
                String.format("Link do zmiany hasła: %s", requestURL));
        existingUser.setToken(token);
        existingUser.setTokenAvailability(TokenAvailability.AVAILABLE);
        existingUser.setTokenDate(LocalDateTime.now());
        userRepository.save(existingUser);
    }

    @Override
    public void changePassword(User user) {
        String token = user.getToken();
        User existingUser = findByToken(token);
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setTokenAvailability(TokenAvailability.UNAVAILABLE);
        userRepository.save(existingUser);
    }
}
