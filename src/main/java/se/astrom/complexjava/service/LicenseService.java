package se.astrom.complexjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.Microsoft365LicenseDto;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.Microsoft365LicenseRepository;

@Service
public class LicenseService {

    private final Microsoft365LicenseRepository licenseRepository;
    private final Microsoft365LicensesMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(LicenseService.class);

    public LicenseService(Microsoft365LicenseRepository licenseRepository, Microsoft365LicensesMapper mapper) {
        this.licenseRepository = licenseRepository;
        this.mapper = mapper;
    }

    public Iterable<Microsoft365LicenseDto> getLicenses(){
        return mapper.licenseIterableToLicenseDtoIterable(licenseRepository.findAll());
    }

    public Microsoft365LicenseDto createLicense(Microsoft365LicenseDto licenseDto){
        logger.info("licenseDto: " + licenseDto);
        return mapper.microsoft365LicenseToMicrosoft365LicenseDto(licenseRepository.save(mapper.microsoft365LicenseDtoToMicrosoft365License(licenseDto)));
    }
}
