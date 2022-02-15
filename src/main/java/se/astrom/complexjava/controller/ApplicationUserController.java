package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.astrom.complexjava.dto.ApplicationUserGetDto;
import se.astrom.complexjava.dto.ApplicationUserPostDto;
import se.astrom.complexjava.exception.AppAuthorizationException;
import se.astrom.complexjava.exception.ControllerEntityNotFoundException;
import se.astrom.complexjava.exception.ServiceEntityNotFoundException;
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
        try {
            var user = appUserService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (ServiceEntityNotFoundException e){
            throw new ControllerEntityNotFoundException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("signup")
    public ResponseEntity<ApplicationUserGetDto> userSignup(@RequestBody ApplicationUserPostDto appUserPostDto){
        var createdUser = appUserService.createUser(appUserPostDto, "USER");
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("createUserWithRole")
    public ResponseEntity<ApplicationUserGetDto> createUser(@RequestBody ApplicationUserPostDto appUserPostDto, @RequestParam String role){
        try {
            var createdUser = appUserService.createUser(appUserPostDto, role);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }catch (AppAuthorizationException e){
            throw new ControllerEntityNotFoundException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        try {
            appUserService.deleteUserById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ServiceEntityNotFoundException e){
                throw new ControllerEntityNotFoundException(HttpStatus.NOT_FOUND, e.getMessage());
            }
    }
}
