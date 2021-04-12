package be.technifutur.labo3.repository;

import be.technifutur.labo3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query(value = "UPDATE public.utilisateur SET  ? WHERE utilisateur.id = 1;", nativeQuery = true)
    void save(Map<String, Object> updates, Integer integer);
}
