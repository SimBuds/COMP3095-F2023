package ca.gbc.inventoryservice;

import ca.gbc.inventoryservice.dto.InventoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryServiceApplicationTests extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private WireMockServer eurekaServerMockServer;

    @BeforeEach
    void setup() {
        // Mock for Eureka Server
        eurekaServerMockServer = new WireMockServer(8761);
        eurekaServerMockServer.start();
        eurekaServerMockServer.stubFor(post(urlPathMatching("/eureka/apps/.*"))
                .willReturn(aResponse().withStatus(204)));
    }
    @AfterEach
    void teardown() {
        if (eurekaServerMockServer != null) {
            eurekaServerMockServer.stop();
        }
    }

    @Test
    void checkInventory() throws Exception {
        // Prepare a list of inventory requests
        List<InventoryRequest> inventoryRequests = Collections.singletonList(
                new InventoryRequest("SKU123", 5)
        );
        String inventoryRequestJson = objectMapper.writeValueAsString(inventoryRequests);

        // Perform the test
        mockMvc.perform(MockMvcRequestBuilders.post("/api/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inventoryRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].skuCode").value("SKU123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sufficientStock").isBoolean());
    }
}