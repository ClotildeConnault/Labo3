package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.OrderDTO;
import be.technifutur.labo3.entity.Order;
import be.technifutur.labo3.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/orders")
public class OrderController implements RestControllable<Order, OrderDTO, Integer> {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDTO> getOne(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(orderService.getById(integer));
    }

    @Override
    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.insert(order));
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<Boolean> update(@RequestBody Order order, @PathVariable("id") Integer integer) {
        return ResponseEntity.ok(orderService.update(order, integer));
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(orderService.delete(integer));
    }
}
