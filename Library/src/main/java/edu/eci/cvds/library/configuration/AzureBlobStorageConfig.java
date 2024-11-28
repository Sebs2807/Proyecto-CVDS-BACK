package edu.eci.cvds.library.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Value("${azure.storage.connection-string}")
    private String connectionString;

    @Value("${azure.storage.container-name}")
    private String containerName;

    public String getConnectionString() {
        return connectionString;
    }

    public String getContainerName() {
        return containerName;
    }
}
