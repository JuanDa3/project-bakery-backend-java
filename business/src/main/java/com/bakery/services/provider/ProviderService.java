package com.bakery.services.provider;

import com.bakery.entities.Provider;
import com.bakery.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface ProviderService {

    Provider registerProvider(Provider provider) throws ExceptionRegister;

    void deleteProvider(Integer id) throws ExceptionDelete;

    List<Provider> listProviders() throws ExceptionList;

    Optional<Provider> findProviderById(Integer id) throws ExceptionFind;

    Provider updateProvider(Integer id, Provider providerToUpdate) throws ExceptionUpdate;
}
