package com.project.tankerservice.service;

import com.project.tankerservice.model.Tanker;
import com.project.tankerservice.model.TankerSupplier;
import com.project.tankerservice.repository.TankerSupplierRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class TankerSupplierServiceImpl implements TankerSupplierService {

    private final TankerSupplierRepository tankerSupplierRepository;

    public TankerSupplierServiceImpl(TankerSupplierRepository tankerSupplierRepository){
        this.tankerSupplierRepository = tankerSupplierRepository;
    }


    @Override
    public TankerSupplier register(TankerSupplier tankerSupplier, boolean isWaterSuppliers) throws DuplicateKeyException{
        tankerSupplier.setWaterSuppliers(isWaterSuppliers);
        if(tankerSupplierRepository.existsTankerSupplierByEmail(tankerSupplier.getEmail())){
            throw  new DuplicateKeyException("Tanker Supplier already exists");
        }else{
            return tankerSupplierRepository.save(tankerSupplier);
        }
    }

}
