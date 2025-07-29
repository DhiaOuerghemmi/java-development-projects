import com.dhia.gateway.config.GatewayConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = GatewayConfig.class)
class GatewayConfigTest {

    private final GatewayConfig config = new GatewayConfig();

    @Test void routes_notEmpty() {
        RouteLocator locator = config.customRoutes(new org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder(null));
        assertThat(locator).isNotNull();
    }
}