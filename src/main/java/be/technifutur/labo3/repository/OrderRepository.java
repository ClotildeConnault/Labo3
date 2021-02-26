package be.technifutur.labo3.repository;

import be.technifutur.labo3.dto.OrderDTO;
import be.technifutur.labo3.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
