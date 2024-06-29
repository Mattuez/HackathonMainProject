package com.matheus.hackathonproject.core.email;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Setter
@Getter
@Component
@ConfigurationProperties("hackathon.email")
public class EmailProperties {

    @NotNull
    private String sender;

    private SenderImplementation impl = SenderImplementation.SAND_BOX;

    public enum SenderImplementation {
        SMTP, SAND_BOX
    }
}
