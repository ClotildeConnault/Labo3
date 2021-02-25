package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.OrderDTO;
import be.technifutur.labo3.entity.Order;
import be.technifutur.labo3.entity.User;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService implements Crudable<Order, OrderDTO, Integer> {

    private final OrderRepository orderRepository;
    private final Mapper mapper;

    public OrderService(OrderRepository orderRepository, Mapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getById(Integer integer) {
        return mapper.toOrderDTO(
                orderRepository.findById(integer)
                        .orElseThrow(() -> new NoSuchElementException("L'utilisateur à l'ID " + integer + " n'a pas été trouvé"))
        );
    }

    @Override
    public boolean insert(Order order) {
        Order o = orderRepository.save(order);
        return orderRepository.existsById(o.getId());
    }

    @Override
    public boolean update(Order order, Integer integer) {
        Order oldOrder = this.orderRepository.getOne(integer);
        Order orderToTest = new Order(
                oldOrder.getId(),
                oldOrder.getReference(),
                oldOrder.getCreationDate(),
                oldOrder.getProducts(),
                oldOrder.isPaid(),
                oldOrder.getPaymentMethod(),
                oldOrder.getUser()
        );
        order.setId(integer);
        this.orderRepository.save(order);
        return !orderToTest.equals(this.orderRepository.getOne(integer));
    }

    @Override
    public boolean delete(Integer integer) {
        this.orderRepository.deleteById(integer);
        return this.orderRepository.findById(integer).isEmpty();
    }
}
