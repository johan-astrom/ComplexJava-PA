package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.astrom.complexjava.dto.Microsoft365LicenseDto;
import se.astrom.complexjava.service.LicenseService;

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
    public ResponseEntity<Microsoft365LicenseDto> createLicense(Microsoft365LicenseDto licenseDto){
        var license = licenseService.createLicense(licenseDto);
        return new ResponseEntity<>(license, HttpStatus.CREATED);
    }


}
