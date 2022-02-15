package se.astrom.complexjava;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import se.astrom.complexjava.controller.AzureUserController;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.exception.ControllerEntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class AzureUsersControllerTests {

    private static AzureUserController controller;

    @BeforeAll
    static void instantiate() {
        controller = new AzureUserController(new MockAzureUserService());
    }

    @Test
    public void getAzureUsersShouldReturnListOfAzureUserGetDtos() {
        List<AzureUserGetDto> azureUsers = new ArrayList<>();

        var getResult = controller.getAllAzureUsers();
        var resultBody = Objects.requireNonNull(getResult.getBody());
        resultBody.forEach(azureUsers::add);

        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(azureUsers.size()).isEqualTo(2);
        assertThat(azureUsers.get(0).getDisplayName()).isEqualTo("testName");
        assertThat(azureUsers.get(1).getDisplayName()).isEqualTo("testName2");
    }

    @Test
    public void getAzureUserByIdShouldReturnAzureUserGetDto() {
        var getResult = controller.getAzureUserById(MockAzureUserService.VALID_ID);
        var azureUser = Objects.requireNonNull(getResult.getBody());

        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(azureUser.getDisplayName()).isEqualTo("testName");
    }

    @Test
    public void getAzureUserByIdShouldThrowControllerEntityNotFoundExceptionWhenProvidedWithInvalidId() {
        assertThatThrownBy(() -> controller.getAzureUserById(MockAzureUserService.INVALID_ID))
                .isInstanceOf(ControllerEntityNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"Test entity not found.\"");
    }

    @Test
    public void createAzureUserShouldReturnCreatedAndAzureUserGetDto(){
        var postUser = new AzureUserPostDto(MockAzureUserService.VALID_ID, "testName", "testPrincipal", "testEmail", "testPhone");
        var getResult = controller.createAzureUser(postUser);
        var azureUser = Objects.requireNonNull(getResult.getBody());

        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(azureUser.getDisplayName()).isEqualTo(postUser.getDisplayName());
    }

    @Test
    public void updateAzureUserShouldReturnOKAndAzureUserGetDto(){
        var updateUser = new AzureUserGetDto("testName", "testPrincipal", "testEmail", "testPhone");
        var getResult = controller.updateAzureUser(MockAzureUserService.VALID_ID, updateUser);
        var azureUser = Objects.requireNonNull(getResult.getBody());

        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(azureUser.getDisplayName()).isEqualTo(updateUser.getDisplayName());
    }

    @Test
    public void updateAzureUserByIdShouldThrowControllerEntityNotFoundExceptionWhenCalledWithInvalidId() {
        var updateUser = new AzureUserGetDto("testName", "testPrincipal", "testEmail", "testPhone");
        assertThatThrownBy(() -> controller.updateAzureUser(MockAzureUserService.INVALID_ID, updateUser))
                .isInstanceOf(ControllerEntityNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"Test entity not found.\"");
    }
}
