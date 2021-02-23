package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserService implements Crudable<User, UserDTO, Integer> {

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
        return mapper.toUserDTO(userRepository.findById(integer).orElseThrow(() -> new NoSuchElementException()));
    }

    @Override
    public boolean insert(User user) {
        User newUser = this.userRepository.save(user);
        return this.userRepository.findById(newUser.getId()).isPresent();
    }

    @Override
    public boolean update(User user, Integer integer) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
