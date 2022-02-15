package se.astrom.complexjava.service;

import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;

public interface AzureUserService {
    AzureUserGetDto getAzureUserById(String id);

    Iterable<AzureUserGetDto> getAzureUsers();

    AzureUserGetDto createAzureUser(AzureUserPostDto azureUserPostDto);

    AzureUserGetDto updateAzureUser(String id, AzureUserGetDto azureUserGetDto);

    void deleteAzureUserById(String id);
}
