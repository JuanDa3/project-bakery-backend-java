package com.bakery.apirest;

import com.bakery.entities.Provider;
import com.bakery.exceptions.ExceptionRegister;
import com.bakery.exceptions.ExceptionUpdate;
import com.bakery.services.provider.ProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/providers")
public class ProviderRestController {

    private final ProviderService providerService;

    public ProviderRestController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>createProvider(@RequestBody Provider provider){
        Provider newProvider;
        Map<String, Object> response = new HashMap<>();

        try {
            newProvider = providerService.registerProvider(provider);
        } catch (ExceptionRegister e) {
            response.put("message","Error registering provider");
            response.put("error", e);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "provider registered correctly");
        response.put("product",newProvider);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>showProviderById(@PathVariable Integer id){
        Optional<Provider> provider;
        Map<String, Object> response = new HashMap<>();

        try {
            provider = providerService.findProviderById(id);
        } catch (RuntimeException e) {
            response.put("message","Error in provider");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("provider",provider);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteProvider(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            providerService.deleteProvider(id);
        }catch (RuntimeException e){
            response.put("message","Error in provider");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "provider deleted correctly");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?>listProviders(){
        List<Provider> providersList = providerService.listProviders();
        return new ResponseEntity<>(providersList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateProvider(@RequestBody Provider provider, @PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        Provider providerUpdated;

        try {
            providerUpdated = providerService.updateProvider(id, provider);
        } catch (ExceptionUpdate e) {
            response.put("message","Error in provider");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","provider updated correctly");
        response.put("provider",providerUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
