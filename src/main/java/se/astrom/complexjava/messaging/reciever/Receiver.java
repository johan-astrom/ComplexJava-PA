package se.astrom.complexjava.messaging.reciever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import se.astrom.complexjava.dto.AzureGroupDto;
import se.astrom.complexjava.messaging.config.JMSConfig;
import se.astrom.complexjava.service.AzureGroupService;



@Component
public class Receiver {

    private final AzureGroupService groupService;
    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public Receiver(AzureGroupService groupService) {
        this.groupService = groupService;
    }

    @JmsListener(destination = JMSConfig.M_365_LICENSES_IN_QUEUE)
    public void syncGroupMembers(@Payload Iterable<AzureGroupDto> groupDtos){
        for(AzureGroupDto group : groupDtos){
            groupService.createAzureGroup(group);
            logger.info("Group persisted. Group: " + group);
        }
    }
}
