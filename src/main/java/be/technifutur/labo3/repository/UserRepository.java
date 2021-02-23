package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
