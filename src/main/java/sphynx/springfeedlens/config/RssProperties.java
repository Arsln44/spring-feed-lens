package sphynx.springfeedlens.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "rss")
@Getter
@Setter
public class RssProperties {
    private List<URL> urls;
}
