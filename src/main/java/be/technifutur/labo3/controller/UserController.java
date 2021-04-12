package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.dto.UserUpdateDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/users")
public class UserController implements RestControllable<User, UserDTO, Integer> {

    private final UserService userService;
    private final Mapper mapper;

    public UserController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(this.userService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.userService.getById(integer));
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.insert(user));
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody User user, @PathVariable("id") Integer integer) {

        return ResponseEntity.ok(this.userService.update(user, integer));
    }


    @PatchMapping(path = "/{id}")
    public ResponseEntity<Boolean> partialUpdate(@RequestBody UserUpdateDTO userUpdate, @PathVariable("id") Integer id) {
        System.out.println("CONTROLLER");
        this.userService.partialUpdate(userUpdate, id);
        System.out.println("CONTROLLER2");
        return ResponseEntity.ok(this.userService.partialUpdate(userUpdate, id));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.userService.delete(integer));
    }

   /* @PostMapping(path = "/auth")
    public ResponseEntity<User> auth(@RequestBody UserDTO user) {
        return ResponseEntity.ok(this.userService.auth(user));
    }*/

    @PostMapping(path = "/connected")
    public ResponseEntity<UserDTO> getUserWithPseudo(@RequestBody User user) {
        return ResponseEntity.ok(this.mapper.toUserDTO((User)this.userService.loadUserByUsername(user.getUsername())));
    }
}
