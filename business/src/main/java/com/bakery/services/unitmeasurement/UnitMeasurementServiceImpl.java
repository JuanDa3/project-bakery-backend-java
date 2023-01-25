package com.bakery.services.unitmeasurement;

import com.bakery.entities.UnitMeasurement;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.repositories.UnitMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitMeasurementServiceImpl implements UnitMeasurementService{

    private final UnitMeasurementRepository unitMeasurementRepository;

    public UnitMeasurementServiceImpl(UnitMeasurementRepository unitMeasurementRepository) {
        this.unitMeasurementRepository = unitMeasurementRepository;
    }

    @Override
    public String registerUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionRegister {
        unitMeasurementRepository.save(unitMeasurement);
        return "Unit Measurement saved correctly";
    }

    @Override
    public String deleteUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionDelete {
        unitMeasurementRepository.delete(unitMeasurement);
        return "Unit Measurement deleted";
    }

    @Override
    public List<UnitMeasurement> listUnitMeasurement() throws ExceptionList {
        return unitMeasurementRepository.findAll();
    }
}
