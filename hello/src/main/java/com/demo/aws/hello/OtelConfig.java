package com.demo.aws.hello;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.contrib.aws.resource.EksResource;
import io.opentelemetry.contrib.awsxray.AwsXrayIdGenerator;
import io.opentelemetry.contrib.awsxray.propagator.AwsXrayPropagator;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtelConfig {

//    @Bean
//    public OpenTelemetry getOpenTelemetry(){
//        String exporterEndpoint = System.getenv("OTEL_EXPORTER_OTLP_ENDPOINT");
//        if (exporterEndpoint == null) {
//            exporterEndpoint = "http://localhost:4317";
//        }
//        return OpenTelemetrySdk.builder()
//
//                // This will enable your downstream requests to include the X-Ray trace header
//                .setPropagators(
//                        ContextPropagators.create(
//                                TextMapPropagator.composite(
//                                        W3CTraceContextPropagator.getInstance(), AwsXrayPropagator.getInstance())))
//
//                // This provides basic configuration of a TracerProvider which generates X-Ray compliant IDs
//                .setTracerProvider(
//                        SdkTracerProvider.builder()
//                                .addSpanProcessor(
//                                        BatchSpanProcessor.builder(OtlpGrpcSpanExporter.builder().setEndpoint(exporterEndpoint).build()).build())
//                                .setResource(Resource.getDefault().merge(EksResource.get()).merge(
//                                        Resource.builder()
//                                                .put("service.name", "hello")
//                                                .build()))
//                                .setIdGenerator(AwsXrayIdGenerator.getInstance())
//                                .build())
//
//                .buildAndRegisterGlobal();
//    }
}
