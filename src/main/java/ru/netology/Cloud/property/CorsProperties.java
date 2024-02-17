package ru.netology.Cloud.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "allowed")
public class CorsProperties {
    private String path;
    private boolean credentials;
    private String origins;
    private String methods;
}
