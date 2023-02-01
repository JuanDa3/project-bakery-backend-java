package com.bakery.apirest;

import com.bakery.entities.UnitMeasurement;
import com.bakery.exceptions.ExceptionFind;
import com.bakery.services.unitmeasurement.UnitMeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/unitmeasurement")
public class UnitMeasurementRestController {

    private final UnitMeasurementService unitMeasurementService;

    public UnitMeasurementRestController(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    @PostMapping
    public ResponseEntity<?>createUnitMeasurement(@RequestBody UnitMeasurement unitMeasurement){
        UnitMeasurement newUnitMeasurement;
        Map<String, Object> response = new HashMap<>();

        try {
            newUnitMeasurement = unitMeasurementService.registerUnitMeasurement(unitMeasurement);
        } catch (RuntimeException e) {
            response.put("message","Error registering unit measurement");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "category registered correctly");
        response.put("category",newUnitMeasurement.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>showUnitMeasurementById(@PathVariable Integer id){
        Optional<UnitMeasurement> unitMeasurement;
        Map<String, Object> response = new HashMap<>();

        try {
            unitMeasurement = unitMeasurementService.findUnitMeasurementById(id);
        } catch (ExceptionFind e) {
            response.put("message","Error in category");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "category registered correctly");
        response.put("category",unitMeasurement);
        return new ResponseEntity<>(unitMeasurement, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUnitMeasurement(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            unitMeasurementService.deleteUnitMeasurement(id);
        }catch (RuntimeException e){
            response.put("message","Error in unit measurement");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "unit measurement deleted correctly");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?>listUnitMeasurement(){
        Map<String, Object> response = new HashMap<>();
        List<UnitMeasurement> unitMeasurementList = unitMeasurementService.listUnitMeasurement();
        response.put("unitMeasurement",unitMeasurementList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
