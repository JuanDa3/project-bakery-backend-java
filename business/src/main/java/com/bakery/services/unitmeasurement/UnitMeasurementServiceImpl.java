package com.bakery.services.unitmeasurement;

import com.bakery.entities.Category;
import com.bakery.entities.UnitMeasurement;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.repositories.UnitMeasurementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitMeasurementServiceImpl implements UnitMeasurementService{

    private final UnitMeasurementRepository unitMeasurementRepository;

    public UnitMeasurementServiceImpl(UnitMeasurementRepository unitMeasurementRepository) {
        this.unitMeasurementRepository = unitMeasurementRepository;
    }

    @Override
    public UnitMeasurement registerUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionRegister {
        validateNull(unitMeasurement);
        validateRepeated(unitMeasurement);
        validateEmpty(unitMeasurement);
        return unitMeasurementRepository.save(unitMeasurement);
    }

    @Override
    public void deleteUnitMeasurement(Integer id) throws ExceptionDelete {
        unitMeasurementRepository.deleteById(id);
    }

    @Override
    public List<UnitMeasurement> listUnitMeasurement() throws ExceptionList {
        return unitMeasurementRepository.findAll();
    }

    @Override
    public Optional<UnitMeasurement> findUnitMeasurementById(Integer id) throws ExceptionFind {
        if(unitMeasurementRepository.findById(id).isEmpty()){
            throw new ExceptionFind("Unit Measurement not exist");
        }
        return unitMeasurementRepository.findById(id);
    }

    @Override
    public Page<UnitMeasurement> listUnitMeasurementPageable(Pageable pageable) throws ExceptionFind {
        return unitMeasurementRepository.listAllPage(pageable);
    }

    private void validateNull(UnitMeasurement unitMeasurement){
        if (unitMeasurement == null){
            throw new ExceptionRegister("Category can't be null");
        }
    }

    private void validateRepeated(UnitMeasurement unitMeasurement){
        if(unitMeasurementRepository.findByName(unitMeasurement.getName()).isPresent()){
            throw new ExceptionRegister("Category is already registered");
        }
    }

    private void validateEmpty(UnitMeasurement unitMeasurement){
        if(unitMeasurement.getName().equals("")){
            throw new ExceptionRegister("Category name can't be empty");
        }
    }
}
