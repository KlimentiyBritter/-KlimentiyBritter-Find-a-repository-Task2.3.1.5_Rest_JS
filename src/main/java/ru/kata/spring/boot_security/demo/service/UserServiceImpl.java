package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional (readOnly = true)
    public Iterable<User> getListOfUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional (readOnly = true)
    public User readUserById(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void findUser(User user, BindingResult bindingResult) {
        if(user.getPassword()==null || user.getPassword().length()==0)
            bindingResult.rejectValue("password", "error password", "password is empty");
        if(user.getRoles().size()==0)
            bindingResult.rejectValue("userRole", "error userRole", "User must have at least one role");
    }

    @Override
    @Transactional (readOnly = true)
    public Boolean isUserExistWithEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.find(email);
        if(user == null)
            throw new UsernameNotFoundException(email);
        return user;
    }
}