package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

        User newUser = this.userRepository.save(user);
        return this.userRepository.findById(newUser.getId()).isPresent();
    }

    @Override
    public boolean update(User user, Integer integer) {

        User oldUser = this.userRepository.getOne(integer);
        System.out.println(oldUser.getFirstName());
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
        System.out.println("Il repassera par là");
        user.setId(integer);
        this.userRepository.save(user);
        return !userToTest.equals(this.userRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.userRepository.deleteById(integer);
        return this.userRepository.findById(integer).isEmpty();
    }

    public User auth(UserDTO user) {

        User userToReturn = null;

        List<UserDTO> users = getAll();
        users.forEach(u -> {
            System.out.println(u.getPseudo());
            System.out.println(u.getPassword());
        });

        boolean isPresent = users.stream()
                .anyMatch(u -> u.getPseudo().equals(user.getPseudo()) && u.getPassword().equals(user.getPassword()));

        if (isPresent) {
           userToReturn = users.stream()
                    .filter(u -> u.getPseudo().equals(user.getPseudo()) && u.getPassword().equals(user.getPassword()))
                    .map(mapper::toUserEntity)
                    .findFirst().orElseThrow(NoSuchElementException::new);
            return userToReturn;
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("J'ai pas trouvé l'utilisateur " + s));
    }
}
