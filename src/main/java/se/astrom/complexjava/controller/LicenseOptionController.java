package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.LicenseOptionDto;
import se.astrom.complexjava.service.LicenseOptionService;

@RestController
@RequestMapping("licenseOptions")
public class LicenseOptionController {

    private LicenseOptionService licenseOptionService;

    public LicenseOptionController(LicenseOptionService licenseOptionService) {
        this.licenseOptionService = licenseOptionService;
    }

    @GetMapping
    public ResponseEntity<Iterable<LicenseOptionDto>> getLicenseOptions(){
        var options = licenseOptionService.getAllLicenseOptions();
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LicenseOptionDto> createLicenseOption(@RequestBody LicenseOptionDto licenseOptionDto, @RequestParam Long licenseId){
        var option = licenseOptionService.createLicenseOption(licenseOptionDto, licenseId);
        return new ResponseEntity<>(option, HttpStatus.CREATED);
    }
}
