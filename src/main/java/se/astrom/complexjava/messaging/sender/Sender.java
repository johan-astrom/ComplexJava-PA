package se.astrom.complexjava.messaging.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(Sender.class);

    public Sender(JmsTemplate jmsTemplate, AzureGroupService azureGroupService) {
        this.jmsTemplate = jmsTemplate;
        this.azureGroupService = azureGroupService;
    }

    //Nightly job that sends the locally stored azure group members to an external receiver for synchronization.
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendSyncMessage() {
        var groups = azureGroupService.getAzureGroups();
        jmsTemplate.convertAndSend(JMSConfig.M_365_LICENSES_OUT_QUEUE, groups);
        logger.info("Group logged. Groups list: " + groups);

    }
}
