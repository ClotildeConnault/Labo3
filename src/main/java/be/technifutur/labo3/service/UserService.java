package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class  UserService implements Crudable<User, UserDTO, Integer> {

    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
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
                oldUser.getPseudo(),
                oldUser.getPassword(),
                oldUser.getAddress()
        );
        user.setId(integer);
        this.userRepository.save(user);
        return !userToTest.equals(this.userRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.userRepository.deleteById(integer);
        return this.userRepository.findById(integer).isEmpty();
    }
}
