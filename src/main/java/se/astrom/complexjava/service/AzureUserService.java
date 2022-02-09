package se.astrom.complexjava.service;

import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.AzureUserDto;
import se.astrom.complexjava.entity.AzureUser;
import se.astrom.complexjava.exception.AppEntityNotFoundException;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.AzureUserRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class AzureUserService {

    private final AzureUserRepository azureUserRepository;
    private Microsoft365LicensesMapper mapper;

    public AzureUserService(AzureUserRepository azureUserRepository, Microsoft365LicensesMapper mapper) {
        this.azureUserRepository = azureUserRepository;
        this.mapper = mapper;
    }

    public AzureUserDto getAzureUserById(String id){
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() -> new AppEntityNotFoundException("Azure user with the specified object id not found."));
        return mapper.azureUserToAzureUserDto(user);
    }

    public Iterable<AzureUserDto> getAzureUsers(){
        return mapper.azureUserIterableToAzureUserDto(azureUserRepository.findAll());
    }

    public void deleteAzureUserById(String id){
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() -> new AppEntityNotFoundException("Azure user with the specified object id not found."));
        azureUserRepository.deleteById(id);
    }
}
