package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.SupplierDTO;
import be.technifutur.labo3.entity.Supplier;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SupplierService  implements Crudable<Supplier, SupplierDTO, Integer>{

    private final SupplierRepository supplierRepository;
    private final Mapper mapper;

    public SupplierService(SupplierRepository supplierRepository, Mapper mapper) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SupplierDTO> getAll() {
        return this.supplierRepository.findAll().stream().map(mapper::toSupplierDTO).collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getById(Integer integer) {
        Supplier supplier = this.supplierRepository.findById(integer).orElseThrow(() -> new NoSuchElementException("Pas de fournisseur avec l'id " + integer));
        return mapper.toSupplierDTO(supplier);
    }

    @Override
    public boolean insert(Supplier supplier) {
        Supplier save = this.supplierRepository.save(supplier);
        return this.supplierRepository.existsById(save.getId());
    }

    @Override
    public boolean update(Supplier supplier, Integer integer) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
