package com.example.demotest.endpoint;

import com.example.demotest.dto.UserDto;
import com.example.demotest.model.User;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> userById(@PathVariable("id") int id) {
        Optional<User> byId = userService.findById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            User save = userRepository.save(user);
            UserDto userDto = modelMapper.map(save, UserDto.class);
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") int id ){
        return userService.update(user,id);
    }




    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id){
        Optional<User> byId = userService.findById(id);
        return byId.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




}
