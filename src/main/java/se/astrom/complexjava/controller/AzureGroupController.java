package se.astrom.complexjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.astrom.complexjava.dto.AzureGroupDto;
import se.astrom.complexjava.dto.AzureGroupMembersDto;
import se.astrom.complexjava.service.AzureGroupService;

@RestController
@RequestMapping("azureGroups")
public class AzureGroupController {

    private AzureGroupService azureGroupService;

    public AzureGroupController(AzureGroupService azureGroupService) {
        this.azureGroupService = azureGroupService;
    }

    @GetMapping
    public ResponseEntity<Iterable<AzureGroupDto>> getAzureGroups(){
        var groups = azureGroupService.getAzureGroups();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @GetMapping("groupMembers")
    public ResponseEntity<AzureGroupMembersDto> getGroupMembers(@RequestParam String groupId){
        var members = azureGroupService.getGroupMembers(groupId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AzureGroupDto> createGroup(@RequestBody AzureGroupDto azureGroupDto){
        var group = azureGroupService.createAzureGroup(azureGroupDto);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @PutMapping("groupMembers")
    public ResponseEntity<AzureGroupMembersDto> addGroupMember(@RequestParam String azureUserId, @RequestParam String azureGroupId){
        var groupMembers = azureGroupService.addMemberToGroup(azureUserId, azureGroupId);
        return new ResponseEntity<>(groupMembers, HttpStatus.OK);
    }

}
