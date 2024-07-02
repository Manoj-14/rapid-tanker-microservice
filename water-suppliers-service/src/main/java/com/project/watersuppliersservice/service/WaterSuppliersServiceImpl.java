package com.project.watersuppliersservice.service;

import com.project.watersuppliersservice.emitters.Emitters;
import com.project.watersuppliersservice.exception.AccountSetupException;
import com.project.watersuppliersservice.exception.SupplierNotFoundException;
import com.project.watersuppliersservice.model.WaterSuppliers;
import com.project.watersuppliersservice.repository.WaterSuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
    public WaterSuppliers update(WaterSuppliers suppliers,UUID userId) throws DuplicateKeyException {
        if(waterSuppliersRepository.existsWaterSuppliersByUserId(userId)){
            WaterSuppliers dbSupplier = waterSuppliersRepository.findWaterSuppliersByUserId(userId);
            dbSupplier.copyClass(suppliers);
            dbSupplier.setAccountSetUpCompleted(!dbSupplier.isNull());
            return waterSuppliersRepository.save(dbSupplier);
        }else {
            throw new SupplierNotFoundException("Supplier account not found");
        }
    }

    @Override
    public WaterSuppliers find(String Email) throws SupplierNotFoundException {

        return null;
    }

    @Override
    public void delete(String email) throws SupplierNotFoundException {
        if(waterSuppliersRepository.existsWaterSuppliersByEmail(email)){
            waterSuppliersRepository.deleteWaterSuppliersByEmail(email);
        }
        else {
            throw new SupplierNotFoundException("Water Supplier not found");
        }
    }

    @Override
    public UUID createInActiveAccount(UUID userId) throws DuplicateKeyException {
        if(waterSuppliersRepository.existsWaterSuppliersByUserId(userId)){
            throw new DuplicateKeyException("User already have a water supplier account");
        }else{
            WaterSuppliers waterSuppliers = new WaterSuppliers();
            waterSuppliers.setUserId(userId);
            waterSuppliers.setAccountSetUpCompleted(false);
            return waterSuppliersRepository.save(waterSuppliers).getId();
        }
    }

    @Override
    public WaterSuppliers findByUserId(UUID userID) throws SupplierNotFoundException, AccountSetupException {
        System.out.println(waterSuppliersRepository.existsWaterSuppliersByUserId(userID));
        WaterSuppliers dbWaterSupplier = waterSuppliersRepository.findWaterSuppliersByUserId(userID);
        if(dbWaterSupplier != null){
            if(dbWaterSupplier.isNull()) throw new AccountSetupException();
            return dbWaterSupplier;
        }else {
            throw new SupplierNotFoundException("Water Supplier Account not exists");
        }
    }
}
