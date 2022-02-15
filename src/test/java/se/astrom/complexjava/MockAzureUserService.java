package se.astrom.complexjava;

import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.exception.ServiceEntityNotFoundException;
import se.astrom.complexjava.service.AzureUserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class MockAzureUserService implements AzureUserService{

    public final static String VALID_ID = "abc123";
    public final static String INVALID_ID = "def456";

    @Override
    public AzureUserGetDto getAzureUserById(String id) {
        if (id.equals(VALID_ID)){
            return new AzureUserGetDto("testName", "testPrincipal", "testEmail", "testPhone");
        }else throw new ServiceEntityNotFoundException("Test entity not found.");
    }

    @Override
    public Iterable<AzureUserGetDto> getAzureUsers() {
        return List.of(new AzureUserGetDto("testName", "testPrincipal", "testEmail", "testPhone"),
                new AzureUserGetDto("testName2", "testPrincipal2", "testEmail2", "testPhone2"));
    }

    @Override
    public AzureUserGetDto createAzureUser(AzureUserPostDto azureUserPostDto) {
        return new AzureUserGetDto(azureUserPostDto.getDisplayName(), azureUserPostDto.getUserPrincipalName(),
                azureUserPostDto.getEmail(), azureUserPostDto.getMobilePhone());
    }

    @Override
    public AzureUserGetDto updateAzureUser(String id, AzureUserGetDto azureUserGetDto) {
        if (id.equals(VALID_ID)){
            return azureUserGetDto;
        }else throw new ServiceEntityNotFoundException("Test entity not found.");
    }

    @Override
    public void deleteAzureUserById(String id) {

    }
}
