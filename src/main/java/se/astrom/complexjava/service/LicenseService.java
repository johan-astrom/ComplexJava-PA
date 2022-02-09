package se.astrom.complexjava.service;

import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.Microsoft365LicenseDto;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.Microsoft365LicenseRepository;

@Service
public class LicenseService {

    private final Microsoft365LicenseRepository licenseRepository;
    private final Microsoft365LicensesMapper mapper;

    public LicenseService(Microsoft365LicenseRepository licenseRepository, Microsoft365LicensesMapper mapper) {
        this.licenseRepository = licenseRepository;
        this.mapper = mapper;
    }

    public Iterable<Microsoft365LicenseDto> getLicenses(){
        return mapper.licenseIterableToLicenseDtoIterable(licenseRepository.findAll());
    }

    public Microsoft365LicenseDto createLicense(Microsoft365LicenseDto licenseDto){
        return mapper.microsoft365LicenseToMicrosoft365LicenseDto(licenseRepository.save(mapper.microsoft365LicenseDtoToMicrosoft365License(licenseDto)));
    }
}
