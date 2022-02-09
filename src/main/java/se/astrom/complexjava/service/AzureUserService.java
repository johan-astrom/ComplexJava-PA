package se.astrom.complexjava.service;

import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.entity.AzureUser;
import se.astrom.complexjava.exception.AppEntityNotFoundException;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.AzureUserRepository;

@Service
public class AzureUserService {

    private final AzureUserRepository azureUserRepository;
    private Microsoft365LicensesMapper mapper;

    public AzureUserService(AzureUserRepository azureUserRepository, Microsoft365LicensesMapper mapper) {
        this.azureUserRepository = azureUserRepository;
        this.mapper = mapper;
    }

    public AzureUserGetDto getAzureUserById(String id){
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Azure user with the specified object id not found."));
        return mapper.azureUserToAzureUserDto(user);
    }

    public Iterable<AzureUserGetDto> getAzureUsers(){
        return mapper.azureUserIterableToAzureUserDto(azureUserRepository.findAll());
    }

    public AzureUserGetDto createAzureUser(AzureUserPostDto azureUserPostDto){
        return mapper.azureUserToAzureUserDto(azureUserRepository.save(mapper.azureUserPostDtoToAzureUser(azureUserPostDto)));
    }

    public AzureUserGetDto updateAzureUser(String id, AzureUserGetDto azureUserGetDto){
        AzureUser userToUpdate = azureUserRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Azure user with the specified object id not found."));
        userToUpdate.setDisplayName(azureUserGetDto.getDisplayName());
        userToUpdate.setEmail(azureUserGetDto.getEmail());
        userToUpdate.setUserPrincipalName(azureUserGetDto.getUserPrincipalName());
        userToUpdate.setMobilePhone(azureUserGetDto.getMobilePhone());

        return mapper.azureUserToAzureUserDto(azureUserRepository.save(userToUpdate));
    }

    public void deleteAzureUserById(String id){
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() ->
                new AppEntityNotFoundException("Azure user with the specified object id not found."));
        azureUserRepository.deleteById(id);
    }
}
