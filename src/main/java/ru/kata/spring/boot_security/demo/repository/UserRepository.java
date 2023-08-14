package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    @EntityGraph(attributePaths = {"userRoles"})
    User find(String username);
}
