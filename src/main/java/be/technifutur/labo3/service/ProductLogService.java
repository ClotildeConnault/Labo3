package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.ProductDTO;
import be.technifutur.labo3.dto.ProductLogDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.ProductLog;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.ProductLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ProductLogService {

    private final ProductLogRepository repo;
    private final Mapper mapper;

    public ProductLogService(ProductLogRepository repo, Mapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<ProductLogDTO> getAllLogs() {
        return repo.findAll()
                .stream()
                .map(mapper::toProductLogDTO)
                .collect(Collectors.toList());
    }

    public List<ProductLogDTO> getLogsForSpecificProduct(int productId) {
        return repo.findByProductId(productId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(mapper::toProductLogDTO)
                .collect(Collectors.toList());
    }

    public List<ProductLogDTO> getLogsForSpecificUser(int userId) {
        return repo.findByUserId(userId)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(mapper::toProductLogDTO)
                .collect(Collectors.toList());
    }

    public boolean insert(ProductLog log) {
        ProductLog l = repo.save(log);
        return repo.existsById(l.getId());
    }


}
