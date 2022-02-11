package se.astrom.complexjava.service;

import org.springframework.stereotype.Service;
import se.astrom.complexjava.dto.AzureGroupDto;
import se.astrom.complexjava.dto.AzureGroupMembersDto;
import se.astrom.complexjava.exception.ServiceEntityNotFoundException;
import se.astrom.complexjava.mapper.Microsoft365LicensesMapper;
import se.astrom.complexjava.repository.AzureGroupRepository;
import se.astrom.complexjava.repository.AzureUserRepository;

@Service
public class AzureGroupService {

    private final AzureGroupRepository groupRepository;
    private final AzureUserRepository userRepository;
    private final Microsoft365LicensesMapper mapper;

    public AzureGroupService(AzureGroupRepository groupRepository, AzureUserRepository userRepository, Microsoft365LicensesMapper mapper) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public Iterable<AzureGroupDto> getAzureGroups(){
        return mapper.azureGroupIterableToAzureGroupDtoIterable(groupRepository.findAll());
    }

    public AzureGroupMembersDto getGroupMembers(String azureGroupId){
        var group = groupRepository.findById(azureGroupId).orElseThrow(() -> new ServiceEntityNotFoundException("No group found with the specified id."));
        return mapper.azureGroupToAzureGroupMembersDto(group);
    }

    public AzureGroupDto createAzureGroup(AzureGroupDto azureGroupDto){
        var group = mapper.azureGroupDtoToAzureGroup(azureGroupDto);
        group.setAzureObjectId(azureGroupDto.getAzureObjectId());
        return mapper.azureGroupToAzureGroupDto(groupRepository.save(group));
    }

    public AzureGroupMembersDto addMemberToGroup(String azureUserId, String azureGroupId){
        var user = userRepository.findById(azureUserId).orElseThrow(() -> new ServiceEntityNotFoundException("No user found with the specified id."));
        var group = groupRepository.findById(azureGroupId).orElseThrow(() -> new ServiceEntityNotFoundException("No group found with the specified id."));
        user.addToGroup(group);
        userRepository.save(user);
        return mapper.azureGroupToAzureGroupMembersDto(groupRepository.save(group));
    }



}
