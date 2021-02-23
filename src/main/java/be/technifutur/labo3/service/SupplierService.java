package be.technifutur.labo3.service;

import be.technifutur.labo3.dto.SupplierDTO;
import be.technifutur.labo3.entity.Supplier;

import java.util.List;

public class SupplierService  implements Crudable<Supplier, SupplierDTO, Integer>{
    @Override
    public List<SupplierDTO> getAll() {
        return null;
    }

    @Override
    public SupplierDTO getById(Integer integer) {
        return null;
    }

    @Override
    public boolean insert(Supplier supplier) {
        return false;
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
