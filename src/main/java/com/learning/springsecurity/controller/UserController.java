    package com.learning.springsecurity.controller;

    import com.learning.springsecurity.entity.User;
    import com.learning.springsecurity.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/user")
    public class UserController {
        @Autowired
        UserService userService;

        @PostMapping("/add")
        public ResponseEntity<String> addUser(@RequestBody User user){
            userService.addUserDetails(user);
            return new ResponseEntity<>("User Created",HttpStatus.CREATED);
        }

        @PreAuthorize("hasAuthority('ROLE_USER')")
        @GetMapping("/get")
        public ResponseEntity<?> getUserById(@RequestParam("id") Long userId){
            return new ResponseEntity<>(userService.findById(userId),HttpStatus.OK);
        }

        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        @GetMapping("/all")
        public ResponseEntity<?> getAll(){
            return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
        }
    }
