package com.uphf.livedemo;

import com.mongodb.client.MongoClient;
import com.uphf.livedemo.configuration.EmbeddedMongoTestConfiguration;
import com.uphf.livedemo.persistance.dto.ProductMongoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Import(EmbeddedMongoTestConfiguration.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoClient mongo;

    @BeforeEach
    public void setUp() {
        mongoTemplate.dropCollection(ProductMongoDto.class);
    }

    @Test
    public void shouldReturn200andProduct_whenFindById() throws Exception {
        // Arrange
        var productSaved = new ProductMongoDto(
                UUID.nameUUIDFromBytes("Existing Product".getBytes()),
                "Existing Product",
                20.0
        );

        mongoTemplate.save(productSaved);

        // Act & Assert
        String id = UUID.nameUUIDFromBytes("Existing Product".getBytes()).toString();

        String expectedProduct = """
               {"name":"Existing Product","price":20.0}
               """;

        mockMvc.perform(get("/product/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expectedProduct.trim())));
    }

    @Test
    public void shouldReturn200andProductId_whenProductIsGivenToBeSaved() throws Exception {
        // Arrange
        String productToSave = """
               {
                    "name": "Test Product",
                    "price": 10.0
               }
               """;

        // Act
        MockHttpServletResponse result = mockMvc.perform(post("/product")
                .content(productToSave)
                .contentType("application/json")
        ).andReturn().getResponse();

        // Assert
        UUID expectedId = UUID.nameUUIDFromBytes("Test Product".getBytes());

        assertThat(result.getStatus()).isEqualTo(200);
        assertThat(result.getContentAsString()).isEqualTo(expectedId.toString());

        ProductMongoDto savedProduct = mongoTemplate.findAll(ProductMongoDto.class).getFirst();
        ProductMongoDto expectedProduct = new ProductMongoDto(expectedId, "Test Product", 10.0);

        assertThat(savedProduct).isEqualTo(expectedProduct);
    }

}
