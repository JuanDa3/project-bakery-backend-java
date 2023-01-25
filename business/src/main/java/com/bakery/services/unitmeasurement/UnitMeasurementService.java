package com.bakery.services.unitmeasurement;

import com.bakery.entities.Product;
import com.bakery.entities.UnitMeasurement;
import com.bakery.exceptions.ExceptionDelete;
import com.bakery.exceptions.ExceptionList;
import com.bakery.exceptions.ExceptionRegister;

import java.util.List;

public interface UnitMeasurementService {

    String registerUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionRegister;

    String deleteUnitMeasurement(UnitMeasurement unitMeasurement) throws ExceptionDelete;

    List<UnitMeasurement> listUnitMeasurement() throws ExceptionList;
}
