package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.AzureUserGetDto;
import se.astrom.complexjava.dto.AzureUserPostDto;
import se.astrom.complexjava.exception.AppEntityNotFoundException;
import se.astrom.complexjava.service.AzureUserService;

@RestController
@RequestMapping("azureUsers")
public class AzureUserController {

    private AzureUserService azureUserService;

    public AzureUserController(AzureUserService azureUserService) {
        this.azureUserService = azureUserService;
    }

    @GetMapping
    public ResponseEntity<Iterable<AzureUserGetDto>> getAllAzureUsers(){
        var users = azureUserService.getAzureUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AzureUserGetDto> getAzureUserById(@PathVariable String id){
        try {
            var user = azureUserService.getAzureUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);

        }catch(AppEntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<AzureUserGetDto> createAzureUser(@RequestBody AzureUserPostDto azureUserPostDto){
        var user = azureUserService.createAzureUser(azureUserPostDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AzureUserGetDto> updateAzureUser(@PathVariable String id, @RequestBody AzureUserGetDto azureUserGetDto){
        var user = azureUserService.updateAzureUser(id, azureUserGetDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAzureUser(@PathVariable String id){
        azureUserService.deleteAzureUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
