package com.o4.mobility.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Moved @EnableJpaAuditing annotation here from Main Applicaiton class
 * so Test annotated @WebMvcTest run successfully, otherwise in test which do not require
 * application context will still be loading JPA Auditing which is unnecessary
 *
 * @EnableJpaAuditing is required when we want to add auditing to the entities
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {}