package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.model.WaterSuppliers;
import com.project.watersuppliersservice.repository.WaterSuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class WaterSuppliersServiceImpl implements WaterSuppliersService {

    private WaterSuppliersRepository waterSuppliersRepository;

    @Autowired
    public WaterSuppliersServiceImpl(WaterSuppliersRepository waterSuppliersRepository){
        this.waterSuppliersRepository = waterSuppliersRepository;
    }

    @Override
    public WaterSuppliers register(WaterSuppliers suppliers) throws DuplicateKeyException {
        if(waterSuppliersRepository.existsWaterSuppliersByEmail(suppliers.getEmail())){
            throw new DuplicateKeyException("Water suppliers already exists");
        }else{
            suppliers = waterSuppliersRepository.save(suppliers);
        }
        return suppliers;
    }
}
