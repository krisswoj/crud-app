package com.finestmedia.finestmedia;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        private static final DockerImageName DB_IMAGE_NAME = DockerImageName.parse("postgres");
        private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DB_IMAGE_NAME.withTag("13.3-alpine"));

        private static void startContainers() {
            Startables.deepStart(Stream.of(postgres)).join();
        }

        private static Map<String, Object> createTestContainerConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgres.getJdbcUrl(),
                    "spring.datasource.username", postgres.getUsername(),
                    "spring.datasource.password", postgres.getPassword(),
                    "spring.flyway.enabled", "true"
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            startContainers();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers", createTestContainerConnectionConfiguration()
            );

            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}

