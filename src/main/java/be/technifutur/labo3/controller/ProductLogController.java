package be.technifutur.labo3.controller;

import be.technifutur.labo3.dto.ProductLogDTO;
import be.technifutur.labo3.entity.ProductLog;
import be.technifutur.labo3.service.ProductLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/plogs")
public class ProductLogController {

    private final ProductLogService service;

    public ProductLogController(ProductLogService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<ProductLogDTO>> getAllLogs() {
        return ResponseEntity.ok(service.getAllLogs());
    }

    @GetMapping(path = "/product/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<ProductLogDTO>> getLogsForSpecificProduct(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.getLogsForSpecificProduct(integer));
    }

    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<ProductLogDTO>> getLogsForSpecificUser(@PathVariable("id") Integer integer) {
        return ResponseEntity.ok(service.getLogsForSpecificUser(integer));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Boolean> insert(@RequestBody ProductLog log) {
        return ResponseEntity.ok(service.insert(log));
    }
}
