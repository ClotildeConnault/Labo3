package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.dto.UserUpdateDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.UserRepository;
import be.technifutur.labo3.util.JsonNullableUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserService implements Crudable<User, UserDTO, Integer>, UserDetailsService {

    private final UserRepository userRepository;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(mapper::toUserDTO)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public UserDTO getById(Integer integer) {
        return mapper.toUserDTO(userRepository.findById(integer).orElseThrow(() -> new NoSuchElementException("L'utilisateur' avec l'id " + integer + " n'a pas été trouvé")));
    }

    @Override
    public boolean insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);

        User newUser = this.userRepository.save(user);
        return this.userRepository.findById(newUser.getId()).isPresent();
    }

    @Override
    public boolean update(User user, Integer integer) {

        User oldUser = this.userRepository.getOne(integer);
        User userToTest = new User(
                oldUser.getId(),
                oldUser.getFirstName(),
                oldUser.getLastName(),
                oldUser.getAccessLevel(),
                oldUser.getUsername(),
                oldUser.getPassword(),
                oldUser.getAddress(),
                oldUser.getPurchases(),
                oldUser.isAccountNonExpired(),
                oldUser.isAccountNonLocked(),
                oldUser.isCredentialsNonExpired(),
                oldUser.isEnabled()
        );
        user.setId(integer);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return !userToTest.equals(this.userRepository.getOne(integer));
    }

    public boolean partialUpdate(UserUpdateDTO userUpdate, Integer integer) {

        User oldUser = this.userRepository.getOne(integer);

        User userToTest = new User(
                oldUser.getId(),
                oldUser.getFirstName(),
                oldUser.getLastName(),
                oldUser.getAccessLevel(),
                oldUser.getUsername(),
                oldUser.getPassword(),
                oldUser.getAddress(),
                oldUser.getPurchases(),
                oldUser.isAccountNonExpired(),
                oldUser.isAccountNonLocked(),
                oldUser.isCredentialsNonExpired(),
                oldUser.isEnabled()
        );

        JsonNullableUtils.changeIfPresent(userUpdate.getFirstName(), userToTest::setFirstName);
        JsonNullableUtils.changeIfPresent(userUpdate.getLastName(), userToTest::setLastName);
        JsonNullableUtils.changeIfPresent(userUpdate.getId(), userToTest::setId);
        JsonNullableUtils.changeIfPresent(userUpdate.getAccessLevel(), userToTest::setAccessLevel);
        JsonNullableUtils.changeIfPresent(userUpdate.getUsername(), userToTest::setUsername);
        JsonNullableUtils.changeIfPresent(userUpdate.getPassword(), e -> userToTest.setPassword(passwordEncoder.encode(e)));;
        JsonNullableUtils.changeIfPresent(userUpdate.getFirstName(), userToTest::setFirstName);
        JsonNullableUtils.changeIfPresent(userUpdate.getFirstName(), userToTest::setFirstName);
        JsonNullableUtils.changeIfPresent(userUpdate.getFirstName(), userToTest::setFirstName);

        this.userRepository.save(userToTest);
        return !userToTest.equals(this.userRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.userRepository.deleteById(integer);
        return this.userRepository.findById(integer).isEmpty();
    }

//    public User auth(UserDTO user) {
//
//        User userToReturn = null;
//
//        List<UserDTO> users = getAll();
//        users.forEach(u -> {
//            System.out.println(u.getUsername());
//            System.out.println(u.getPassword());
//        });
//
//        boolean isPresent = users.stream()
//                .anyMatch(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()));
//
//        if (isPresent) {
//           userToReturn = users.stream()
//                    .filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()))
//                    .map(mapper::toUserEntity)
//                    .findFirst().orElseThrow(NoSuchElementException::new);
//            return userToReturn;
//        }
//
//        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("J'ai pas trouvé l'utilisateur " + s));
    }

    public boolean userExistByUsername(String userName){
        return this.userRepository.findByUsername(userName).isPresent();
    }
}
