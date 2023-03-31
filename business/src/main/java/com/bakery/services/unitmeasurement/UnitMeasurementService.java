package com.bakery.services.unitmeasurement;

import com.bakery.entities.Product;
import com.bakery.entities.UnitMeasurement;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UnitMeasurementService {

    UnitMeasurement registerUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionRegister;

    void deleteUnitMeasurement(Integer id) throws ExceptionDelete;

    List<UnitMeasurement> listUnitMeasurement() throws ExceptionList;

    Optional<UnitMeasurement>findUnitMeasurementById(Integer id) throws ExceptionFind;

    Page<UnitMeasurement> listUnitMeasurementPageable(Pageable pageable) throws ExceptionFind;
}
