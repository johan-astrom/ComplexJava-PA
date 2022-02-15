package se.astrom.complexjava;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.astrom.complexjava.controller.AzureUserController;
import se.astrom.complexjava.dto.ApplicationUserPostDto;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.exception.AppJwtException;
import se.astrom.complexjava.repository.AzureUserRepository;
import se.astrom.complexjava.service.AzureUserService;
import org.hamcrest.Matcher;

import org.springframework.security.access.AccessDeniedException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ComplexJavaPaApplicationTests {

    @Autowired
    private AzureUserService azureUserService;

    @Autowired
    private AzureUserController azureUserController;

    @Autowired
    private AzureUserRepository azureUserRepository;

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private TestRestTemplate testClient;
//
//    @LocalServerPort
//    private int port;

    @Test
    void contextLoads() {
        assertThat(azureUserController).isNotNull();
        assertThat(azureUserRepository).isNotNull();
        assertThat(azureUserService).isNotNull();
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

    @Test
    public void azureUserContext() throws Exception {
        String token = getToken("admin", "password");

        var postAzureUser = new AzureUserPostDto(MockAzureUserService.VALID_ID, "testName",
                "testPrincipal", "testEmail", "testPhone");
        ObjectMapper mapper = new ObjectMapper();
        String azureUserJson = mapper.writeValueAsString(postAzureUser);

        mvc.perform(post("/azureUsers")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json;charset=UTF-8")
                        .content(azureUserJson)
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.displayName", is("testName")));

        mvc.perform(get("/azureUsers/" + MockAzureUserService.VALID_ID)
                        .header("Authorization", "Bearer " + token)
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.displayName", is("testName")));

        var patchUser = new AzureUserGetDto("testName2",
                "testPrincipal2", "testEmail2", "testPhone2");
        String patchUserJson = mapper.writeValueAsString(patchUser);

        mvc.perform(patch("/azureUsers/" + MockAzureUserService.VALID_ID)
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json;charset=UTF-8")
                        .content(patchUserJson)
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.displayName", is("testName2")));

        mvc.perform(delete("/azureUsers/" + MockAzureUserService.VALID_ID)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }

    @Test
    public void userContext() throws Exception {
        String token = getToken("admin", "password");

        var appUser = new ApplicationUserPostDto("user", "password");
        ObjectMapper mapper = new ObjectMapper();
        String appUserJson = mapper.writeValueAsString(appUser);

        mvc.perform(post("/users/createUserWithRole")
                        .param("role", "USER")
                        .header("Authorization", "Bearer " + token)
                        .contentType("application/json;charset=UTF-8")
                        .content(appUserJson)
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.username", is("user")));
    }

    private String getToken(String username, String password) throws Exception {
        ResultActions result = mvc.perform(post("/authenticate")
                        .with(httpBasic(username, password))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("token").toString();
    }


}
