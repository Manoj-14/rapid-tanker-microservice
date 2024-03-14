package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.emitters.Emitters;
import com.project.watersuppliersservice.exception.SupplierNotFoundException;
import com.project.watersuppliersservice.model.WaterSuppliers;
import com.project.watersuppliersservice.repository.WaterSuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class WaterSuppliersServiceImpl implements WaterSuppliersService {

    private final WaterSuppliersRepository waterSuppliersRepository;
    private final Emitters emitters;
    @Autowired
    public WaterSuppliersServiceImpl(WaterSuppliersRepository waterSuppliersRepository,Emitters emitters){
        this.waterSuppliersRepository = waterSuppliersRepository;
        this.emitters = emitters;
    }

    @Override
    public WaterSuppliers register(WaterSuppliers suppliers) throws DuplicateKeyException {
        if(waterSuppliersRepository.existsWaterSuppliersByEmail(suppliers.getEmail())){
            throw new DuplicateKeyException("Water suppliers already exists");
        }else{
            suppliers = waterSuppliersRepository.save(suppliers);
            if(suppliers.isHasTanker()){
                emitters.registerPrivateTankers(suppliers);
            }
        }
        return suppliers;
    }

    @Override
    public WaterSuppliers update(WaterSuppliers suppliers,String email) throws DuplicateKeyException {
        if(waterSuppliersRepository.existsWaterSuppliersByEmail(email) && !suppliers.getEmail().equals(email) ){
            
        }else {

        }
        return null;
    }

    @Override
    public WaterSuppliers find(String Email) throws SupplierNotFoundException {
        return null;
    }

    @Override
    public void delete(String Email) throws SupplierNotFoundException {

    }
}
