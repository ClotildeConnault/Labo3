package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.UserDTO;
import be.technifutur.labo3.entity.User;

import java.util.List;

public class UserService implements Crudable<User, UserDTO, Integer> {
    @Override
    public List<UserDTO> getAll() {
        return null;
    }

    @Override
    public UserDTO getById(Integer integer) {
        return null;
    }

    @Override
    public boolean insert(User user) {
        return false;
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
