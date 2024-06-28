package pl.coderslab.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.entity.CurrentUser;
import pl.coderslab.entity.User;
import pl.coderslab.entity.UserAvailability;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface UserService {
    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByToken(String token);

    List<User> findByRole(String roleName);

    void addAdmin(User user);

    void update(User user);

    void saveUser(User user);

    void deleteById(CurrentUser currentUser, Long id);

    void enableUser(Long id, UserAvailability enabled);

    void deleteAdmin(CurrentUser currentUser, Long id);

    String processLoginForm(boolean error, User user, Model model);

    void showWelcomePage(CurrentUser currentUser, Model model);

    void generateAndEmailToken(User user, HttpServletRequest request);

    void changePassword(User user);
}
