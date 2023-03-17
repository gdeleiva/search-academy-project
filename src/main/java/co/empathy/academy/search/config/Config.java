package co.empathy.academy.search.config;

import co.empathy.academy.search.connection.Connection;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
public class Config implements AsyncConfigurer {

    @Bean
    public Connection connection(){
        return new Connection(RestClient.builder(new HttpHost("localhost", 9200)).build());
    }
}
