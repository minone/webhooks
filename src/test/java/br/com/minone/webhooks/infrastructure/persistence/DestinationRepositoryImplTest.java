package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DestinationRepositoryImplTest {

    @Autowired
    private DestinationRepository destinationRepository;

    private final String TEST_URL = "testUrl";

    @Test
    public void testRegisterDestination() throws Exception {
        // Arrange
        DestinationId expectedId = new DestinationId(UUID.randomUUID());
        Destination destination = new Destination(expectedId, TEST_URL);

        // Act
        String returnedId = destinationRepository.registerDestination(destination);

        // Assert
        assertEquals(expectedId.getId().toString(), returnedId);
    }

    @Test
    public void testDeleteDestination() throws Exception {

    }

    @Before
    public void setUp() {
    }


    @After
    public void tearDown() {

    }
}