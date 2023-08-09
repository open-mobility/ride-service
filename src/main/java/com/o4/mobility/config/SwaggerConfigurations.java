package com.o4.mobility.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Ride services - Open Mobility", version = "3.0", description = "This is a service related to ride flow, booking and fulfillment."))
public class SwaggerConfigurations {

}
