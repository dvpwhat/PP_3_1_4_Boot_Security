package ru.kata.spring.boot_security.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initilazedDB() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Set<Role> adminRole = new HashSet<>();
        Set<Role> userRole = new HashSet<>();
        Set<Role> allRoles = new HashSet<>();
        adminRole.add(roleService.showUserById(1L));
        userRole.add(roleService.showUserById(2L));
        allRoles.add(roleService.showUserById(1L));
        allRoles.add(roleService.showUserById(2L));
        userService.save(new User("renya", "krokodilov", 12, "admin@mail.com","admin", adminRole));
        userService.save(new User("poppy", "tank",11, "user","user@mail.com", userRole));
        userService.save(new User("ez", "adc",10, "test","test", allRoles));
    }
}
