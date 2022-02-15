package se.astrom.complexjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.entity.AzureUser;
import se.astrom.complexjava.exception.ServiceEntityNotFoundException;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.AzureUserRepository;

import java.util.Objects;

@Service
public class AzureUserServiceImpl implements AzureUserService {

    private final AzureUserRepository azureUserRepository;
    private final Microsoft365LicensesMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(AzureUserServiceImpl.class);

    public AzureUserServiceImpl(AzureUserRepository azureUserRepository, Microsoft365LicensesMapper mapper) {
        this.azureUserRepository = azureUserRepository;
        this.mapper = mapper;
    }

    @Override
    public AzureUserGetDto getAzureUserById(String id) {
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() ->
                new ServiceEntityNotFoundException("Azure user with the specified object id not found."));
        return mapper.azureUserToAzureUserDto(user);
    }

    @Override
    public Iterable<AzureUserGetDto> getAzureUsers() {
        return mapper.azureUserIterableToAzureUserDto(azureUserRepository.findAll());
    }

    @Override
    public AzureUserGetDto createAzureUser(AzureUserPostDto azureUserPostDto) {
        logger.info("AzureUserPostDTO: " + azureUserPostDto);
        var azureUser = mapper.azureUserPostDtoToAzureUser(azureUserPostDto);
        azureUser.setAzureObjectId(azureUserPostDto.getId());
        return mapper.azureUserToAzureUserDto(azureUserRepository.save(azureUser));
    }

    @Override
    public AzureUserGetDto updateAzureUser(String id, AzureUserGetDto azureUserGetDto) {
        AzureUser userToUpdate = azureUserRepository.findById(id).orElseThrow(() ->
                new ServiceEntityNotFoundException("Azure user with the specified object id not found."));
        userToUpdate.setDisplayName(
                Objects.requireNonNullElse(azureUserGetDto.getDisplayName(), userToUpdate.getDisplayName()));
        userToUpdate.setEmail(
                Objects.requireNonNullElse(azureUserGetDto.getEmail(), userToUpdate.getEmail()));
        userToUpdate.setUserPrincipalName(
                Objects.requireNonNullElse(azureUserGetDto.getUserPrincipalName(), userToUpdate.getUserPrincipalName()));
        userToUpdate.setMobilePhone(
                Objects.requireNonNullElse(azureUserGetDto.getMobilePhone(), userToUpdate.getMobilePhone()));

        return mapper.azureUserToAzureUserDto(azureUserRepository.save(userToUpdate));
    }

    @Override
    public void deleteAzureUserById(String id) {
        AzureUser user = azureUserRepository.findById(id).orElseThrow(() ->
                new ServiceEntityNotFoundException("Azure user with the specified object id not found."));
        azureUserRepository.deleteById(id);
    }
}
