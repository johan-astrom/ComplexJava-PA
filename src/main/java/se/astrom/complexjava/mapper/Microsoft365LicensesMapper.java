package se.astrom.complexjava.mapper;

import org.mapstruct.ReportingPolicy;
import se.astrom.complexjava.dto.*;
import se.astrom.complexjava.entity.*;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface Microsoft365LicensesMapper {

    ApplicationUser appUserPostDtoToAppUser(ApplicationUserPostDto applicationUserPostDto);

    ApplicationUser appUserGetDtoToAppUser(ApplicationUserGetDto applicationUserGetDto);

    ApplicationUserPostDto appUserToAppUserPostDto(ApplicationUser applicationUser);

    ApplicationUserGetDto appUserToAppUserGetDto(ApplicationUser applicationUser);

    Iterable<ApplicationUserGetDto> appUserIterableToAppUserDtoIterable(Iterable<ApplicationUser> applicationUsers);

    AzureGroup azureGroupDtoToAzureGroup(AzureGroupDto azureGroupDto);

    AzureGroupDto azureGroupToAzureGroupDto(AzureGroup azureGroup);

    AzureUser azureUserDtoToAzureUser(AzureUserDto azureUserDto);

    AzureUserDto azureUserToAzureUserDto(AzureUser azureUser);

    LicenseOption licenseOptionDtoToLicenseOption(LicenseOptionDto licenseOptionDto);

    LicenseOptionDto licenseOptionToLicenseOptionDto(LicenseOption licenseOption);

    Microsoft365License microsoft365LicenseDtoToMicrosoft365License(Microsoft365LicenseDto microsoft365LicenseDto);

    Microsoft365LicenseDto microsoft365LicenseToMicrosoft365LicenseDto(Microsoft365License microsoft365License);




}
