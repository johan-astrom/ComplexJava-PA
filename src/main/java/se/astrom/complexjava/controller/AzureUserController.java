package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.AzureUserDto;
import se.astrom.complexjava.exception.AppEntityNotFoundException;
import se.astrom.complexjava.service.AzureUserService;

@RestController
@RequestMapping("azureUsers")
public class AzureUserController {

    private AzureUserService azureUserService;

    @GetMapping
    public ResponseEntity<Iterable<AzureUserDto>> getAllAzureUsers(){
        var users = azureUserService.getAzureUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<AzureUserDto> getAzureUserById(@PathVariable String id){
        try {
            var user = azureUserService.getAzureUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);

        }catch(AppEntityNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<AzureUserDto> createAzureUser(AzureUserDto azureUserDto){
        var user = azureUserService.createAzureUser(azureUserDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AzureUserDto> updateAzureUser(@PathVariable String id, AzureUserDto azureUserDto){
        var user = azureUserService.updateAzureUser(id, azureUserDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAzureUser(@PathVariable String id){
        azureUserService.deleteAzureUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
