package com.bakery.services.provider;

import com.bakery.entities.Product;
import com.bakery.entities.Provider;
import com.bakery.exceptions.*;
import com.bakery.repositories.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService{

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Provider registerProvider(Provider provider) throws ExceptionRegister {
        validateNull(provider);
        providerRepository.save(provider);
        return provider;
    }

    @Override
    public void deleteProvider(Integer id) throws ExceptionDelete {
        providerRepository.deleteById(id);
    }

    @Override
    public List<Provider> listProviders() throws ExceptionList {
        return providerRepository.findAll();
    }

    @Override
    public Optional<Provider> findProviderById(Integer id) throws ExceptionFind {
        return Optional.ofNullable(providerRepository.findById(id).orElseThrow(() -> new ExceptionFind("Provider not exist")));
    }

    @Override
    public Provider updateProvider(Integer id, Provider providerToUpdate) throws ExceptionUpdate {
        if(providerRepository.findById(id).isEmpty()){
            throw new ExceptionUpdate("Provider not exist");
        }
        return providerRepository.save(providerToUpdate);
    }

    private void validateNull(Provider provider){
        if (provider == null){
            throw new ExceptionRegister("Provider can't be null");
        }
    }
}
