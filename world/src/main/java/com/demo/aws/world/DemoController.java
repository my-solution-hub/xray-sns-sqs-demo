package com.demo.aws.world;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.List;

@RestController
public class DemoController {
    @GetMapping
    String sayHello(){
        SqsClient sqsClient = SqsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();
        receiveMessage(sqsClient, "https://sqs.ap-southeast-1.amazonaws.com/613477150601/xray-sns-sqs-demo-demo");
        return "world";
    }
    public void receiveMessage(SqsClient sqsClient, String queueUrl) {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(5)
                    .build();
            for (Message message: sqsClient.receiveMessage(receiveMessageRequest).messages()){
                System.out.println(message.body());
                System.out.println(message.attributes().toString());
                System.out.println(message.messageAttributes().toString());
            }

        } catch (SqsException e) {
            e.printStackTrace();
        }
    }
}
