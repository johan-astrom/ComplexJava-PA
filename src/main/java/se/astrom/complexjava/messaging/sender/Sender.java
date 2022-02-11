package se.astrom.complexjava.messaging.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import se.astrom.complexjava.dto.AzureGroupDto;
import se.astrom.complexjava.dto.AzureGroupMembersDto;
import se.astrom.complexjava.messaging.config.JMSConfig;
import se.astrom.complexjava.service.AzureGroupService;

import java.util.ArrayList;

@Component
public class Sender {

    private final JmsTemplate jmsTemplate;
    private final AzureGroupService azureGroupService;

    public Sender(JmsTemplate jmsTemplate, AzureGroupService azureGroupService) {
        this.jmsTemplate = jmsTemplate;
        this.azureGroupService = azureGroupService;
    }

    //Sends the locally stored azure group members to an external service for synchronization.
    @Scheduled(cron="0 0 0 * * ?")
    public void sendSyncMessage(){
        var groups = azureGroupService.getAzureGroups();
        var groupMembers = new ArrayList<AzureGroupMembersDto>();
        for (AzureGroupDto group : groups) {
            groupMembers.add(azureGroupService.getGroupMembers(group.getAzureObjectId()));
        }
        jmsTemplate.convertAndSend(JMSConfig.M_365_LICENSES_QUEUE, groupMembers);
    }
}
