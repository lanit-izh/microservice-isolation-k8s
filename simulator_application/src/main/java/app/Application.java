package app;

import com.consol.citrus.endpoint.EndpointAdapter;
import com.consol.citrus.endpoint.adapter.StaticEndpointAdapter;
import com.consol.citrus.http.message.HttpMessage;
import com.consol.citrus.message.Message;
import com.consol.citrus.simulator.http.HttpRequestPathScenarioMapper;
import com.consol.citrus.simulator.http.HttpScenarioGenerator;
import com.consol.citrus.simulator.http.SimulatorRestAdapter;
import com.consol.citrus.simulator.scenario.mapper.ScenarioMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;



@SpringBootApplication
public class Application extends SimulatorRestAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public ScenarioMapper scenarioMapper() {
        return new HttpRequestPathScenarioMapper();
    }

    @Override
    public EndpointAdapter fallbackEndpointAdapter() {
        return new StaticEndpointAdapter() {
            @Override
            protected Message handleMessageInternal(Message message) {
                return new HttpMessage().status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        };
    }

    @Bean
    public static HttpScenarioGenerator scenarioGenerator() throws MalformedURLException {
        HttpScenarioGenerator generator = new HttpScenarioGenerator(new UrlResource(System.getenv("SERVICE_URL")+"/v2/api-docs"));
        return generator;
    }
}
