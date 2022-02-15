package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.Microsoft365LicenseDto;
import se.astrom.complexjava.service.LicenseService;

@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
@RestController
@RequestMapping("licenses")
public class LicenseController {

    private LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Microsoft365LicenseDto>> getLicenses(){
        var licenses = licenseService.getLicenses();
        return new ResponseEntity<>(licenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Microsoft365LicenseDto> createLicense(@RequestBody Microsoft365LicenseDto licenseDto){
        var license = licenseService.createLicense(licenseDto);
        return new ResponseEntity<>(license, HttpStatus.CREATED);
    }


}
