package se.astrom.complexjava.service;

import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.LicenseOptionDto;
import se.astrom.complexjava.entity.LicenseOption;
import se.astrom.complexjava.entity.Microsoft365License;
import se.astrom.complexjava.exception.AppEntityNotFoundException;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.LicenseOptionRepository;
import se.astrom.complexjava.repository.Microsoft365LicenseRepository;

@Service
public class LicenseOptionService {

    private LicenseOptionRepository repository;
    private Microsoft365LicenseRepository licenseRepository;
    private Microsoft365LicensesMapper mapper;

    public LicenseOptionService(LicenseOptionRepository repository, Microsoft365LicensesMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Iterable<LicenseOptionDto> getAllLicenseOptions(){
       return mapper.licenseOptionIterableToLicenseOptionDtoIterable(repository.findAll());
    }

    public LicenseOptionDto createLicenseOption(LicenseOptionDto licenseOptionDto, Long licenseId){
        Microsoft365License license = licenseRepository.findById(licenseId).orElseThrow(() ->
                new AppEntityNotFoundException("No license found with the specified id."));
        LicenseOption licenseOption = mapper.licenseOptionDtoToLicenseOption(licenseOptionDto);
        licenseOption.setLicense(license);
        return mapper.licenseOptionToLicenseOptionDto(repository.save(licenseOption));
    }

}
