package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.deleted = 0")
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.deleted = 0")
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.token = ?1 AND u.deleted = 0")
    User findByToken(String token);

    @Query("SELECT u FROM User u LEFT JOIN u.roles r WHERE r.name = ?1 AND u.deleted = 0 ")
    List<User> findAllByRoles(String roles);

}