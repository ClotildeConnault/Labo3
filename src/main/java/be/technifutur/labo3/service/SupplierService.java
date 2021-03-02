package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.SupplierDTO;
import be.technifutur.labo3.entity.Product;
import be.technifutur.labo3.entity.Supplier;
import be.technifutur.labo3.mapper.Mapper;
import be.technifutur.labo3.repository.ProductRepository;
import be.technifutur.labo3.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SupplierService implements Crudable<Supplier, SupplierDTO, Integer>{

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final Mapper mapper;

    public SupplierService(SupplierRepository supplierRepository, ProductRepository productRepository, ProductService productService, ProductRepository productRepository1, Mapper mapper) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository1;
        this.mapper = mapper;
    }

    @Override
    public List<SupplierDTO> getAll() {
        return this.supplierRepository.findAll().stream().map(s -> mapper.toSupplierDTO(s, true)).collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getById(Integer integer) {
        Supplier supplier = this.supplierRepository.findById(integer).orElseThrow(() -> new NoSuchElementException("Pas de fournisseur avec l'id " + integer));
        return mapper.toSupplierDTO(supplier, true);
    }

    @Override
    public boolean insert(Supplier supplier) {
        Supplier save = this.supplierRepository.save(supplier);
        return this.supplierRepository.existsById(save.getId());
    }

    @Override
    public boolean update(Supplier supplier, Integer integer) {
        Supplier old = this.supplierRepository.getOne(integer);
        Supplier toTest = new Supplier(old.getId(),old.getCompanyName(),old.getStatut(),old.getSector(),old.getInsertionDate(),old.getUpdateDate(),old.getProducts(), old.isInactive());
        supplier.setId(integer);
        supplier.setInsertionDate(toTest.getInsertionDate());
        this.supplierRepository.save(supplier);
        return !toTest.equals(this.supplierRepository.getOne(integer));
    }

//    @Override
//    public boolean delete(Integer integer) {
//        this.supplierRepository.deleteById(integer);
//        return !this.supplierRepository.existsById(integer);
//    }

    @Override
    public boolean delete(Integer integer) {
        Supplier toDelete = supplierRepository.getOne(integer);
        toDelete.setInactive(true);
        List<Product> productsToDelete = toDelete.getProducts();
        for (Product p : productsToDelete) {
            p.setInactive(true);
            productRepository.save(p);
        }
        supplierRepository.save(toDelete);
        Supplier verif = supplierRepository.getOne(integer);
        return verif.isInactive();
    }
}
