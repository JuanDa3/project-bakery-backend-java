package com.bakery.repositories;

import com.bakery.entities.UnitMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitMeasurementRepository extends JpaRepository<UnitMeasurement, Integer> {

    @Query("select um from UnitMeasurement um where um.name = :unitMeasurementName")
    Optional<UnitMeasurement>findByName(String unitMeasurementName);

    @Query("select um from UnitMeasurement um")
    Page<UnitMeasurement> listAllPage(Pageable pageable);
}
