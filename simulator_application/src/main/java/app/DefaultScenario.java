package app;

import com.consol.citrus.simulator.scenario.AbstractSimulatorScenario;
import com.consol.citrus.simulator.scenario.Scenario;
import com.consol.citrus.simulator.scenario.ScenarioRunner;
import org.springframework.http.HttpStatus;

@Scenario("Default")
public class DefaultScenario extends AbstractSimulatorScenario {
    @Override
    public void run(ScenarioRunner scenario) {
        scenario
                .http()
                .receive((builder -> builder.get()));

        scenario
                .http()
                .send((builder -> builder
                        .response(HttpStatus.NOT_FOUND)
                        .payload("No scenario found for this request"))
                );
    }
}
