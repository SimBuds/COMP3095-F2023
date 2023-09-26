package ca.gbc.productservice;

import ca.gbc.productservice.repository.ProductRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests extends AbstractContainerBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void createProducts() {

    }

    @Test
    public void getAllProducts() {

    }

    @Test
    public void getProductById() {

    }

    @Test
    public void updateProduct() {

    }

    @Test
    public void deleteProduct() {

    }
}
