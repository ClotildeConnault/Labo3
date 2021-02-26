package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/users")
public class UserController implements RestControllable<User, UserDTO, Integer> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
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

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(this.userService.delete(integer));
    }
}
