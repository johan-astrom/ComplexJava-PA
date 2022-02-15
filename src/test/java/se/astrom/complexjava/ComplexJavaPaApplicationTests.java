package se.astrom.complexjava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplateExtensionsKt;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.security.jwt.JwtResponse;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ComplexJavaPaApplicationTests {

    @Autowired
    private TestRestTemplate testClient;

    @LocalServerPort
    private int port;

    @Test
    void contextLoadsAzureUser() {



//        String azureUserObjectId = "abc123";
//        String endpointUrl = "http://localhost:" + port + "/azureUsers";
//        String authUrl = "http://localhost:" + port + "/authenticate";
//
//        var authenticationResult = testClient.withBasicAuth("admin", "password").
//                postForEntity(authUrl, null, Object.class);
//        assertThat(authenticationResult.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        var token = ((LinkedHashMap<String,String>) Objects.requireNonNull(authenticationResult.getBody())).get("token");
//
//        System.out.println("Token: " + token);
//
//        var postResult = testClient.postForEntity(endpointUrl,
//                new AzureUserPostDto(azureUserObjectId, "testName", "testPrincipal", "testEmail", "testPhone"),
//                AzureUserGetDto.class);
//        assertThat(postResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        var getResult = testClient.getForEntity(endpointUrl, AzureUserGetDto[].class);
//        assertThat(getResult.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(Objects.requireNonNull(getResult.getBody()).length).isGreaterThan(0);
//
//        var getByIdResult = testClient.getForEntity(endpointUrl, AzureUserGetDto.class, azureUserObjectId);
//        assertThat(getByIdResult.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(getByIdResult.getBody()).isNotNull();
//
//        var patchedAzureUser = testClient.patchForObject(endpointUrl, new AzureUserGetDto("testNameUpdated",
//                        "testPrincipalUpdated", "testEmailUpdated", "testPhoneUpdated"),
//                AzureUserGetDto.class, azureUserObjectId);
//        ResponseEntity<AzureUserGetDto> patchResult = ResponseEntity.of(Optional.of(patchedAzureUser));
//        assertThat(patchResult.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}
