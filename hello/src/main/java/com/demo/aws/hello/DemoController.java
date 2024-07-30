package com.demo.aws.hello;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.context.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.util.HashMap;
import java.util.Map;


@RestController
public class DemoController {
    @GetMapping
    public String sayHello(){
//        Span span = GlobalOpenTelemetry
//                .getTracer ("architecture")
//                .spanBuilder ("world")
//                .setSpanKind (SpanKind.SERVER)
//                .setNoParent ()
//                .startSpan ();
//        System.out.println("New Span SpanContext: " + span.getSpanContext());
        SnsClient snsClient = SnsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .build();
        pubTopic(snsClient, "demo", "arn:aws:sns:ap-southeast-1:613477150601:xray-sns-sqs-demo-demo");
        // pubTopic(snsClient, "demo", "arn:aws:sns:ap-southeast-1:613477150601:xray-sns-sqs-demo-demo", span.getSpanContext().getTraceId(), span.getSpanContext().getSpanId());
//        try (Scope innerScope = span.makeCurrent()) {
//
//        } finally {
//            span.end();
//            snsClient.close();
//        }
        return "Hello";
    }

    public void pubTopic(SnsClient snsClient, String message, String topicArn) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();
            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());
            System.out
                    .println(request.messageAttributes().toString());
            System.out
                    .println(result.responseMetadata().toString());

        } catch (SnsException e) {
            e.printStackTrace();
        }
    }

    public void pubTopic(SnsClient snsClient, String message, String topicArn, String traceId, String parentId) {
        try {
            final Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            messageAttributes.put("AWSTraceHeader", MessageAttributeValue.builder()
                    .dataType("Root").stringValue("Jane")
                    .dataType("Parent").stringValue("Jane")
                    .build());
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .messageAttributes(messageAttributes)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = snsClient.publish(request);
            System.out
                    .println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            e.printStackTrace();
        }
    }
}
