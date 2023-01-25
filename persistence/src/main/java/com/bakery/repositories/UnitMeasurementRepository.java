package com.bakery.repositories;

import com.bakery.entities.UnitMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMeasurementRepository extends JpaRepository<UnitMeasurement, Integer> {
}
