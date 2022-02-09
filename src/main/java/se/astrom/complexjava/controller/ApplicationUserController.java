package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.ApplicationUserGetDto;
import se.astrom.complexjava.dto.ApplicationUserPostDto;
import se.astrom.complexjava.service.ApplicationUserService;

@RestController
@RequestMapping("users")
public class ApplicationUserController {

    private final ApplicationUserService appUserService;

    public ApplicationUserController(ApplicationUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseEntity<Iterable<ApplicationUserGetDto>> getUsers() {
        var users = appUserService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApplicationUserGetDto> getUserById(@PathVariable Long id) {
        var user = appUserService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("signup")
    public ResponseEntity<ApplicationUserGetDto> createUser(@RequestBody ApplicationUserPostDto appUserPostDto){
        var createdUser = appUserService.createUser(appUserPostDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        appUserService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
