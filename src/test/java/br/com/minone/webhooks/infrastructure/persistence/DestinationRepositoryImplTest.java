package br.com.minone.webhooks.infrastructure.persistence;

import br.com.minone.webhooks.domain.model.Destination;
import br.com.minone.webhooks.domain.model.DestinationId;
import br.com.minone.webhooks.domain.model.DestinationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DestinationRepositoryImplTest {

    @Autowired
    private DestinationRepository destinationRepository;

    private final String TEST_URL = "http://www.example.com/validTestUrl";

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
        // Arrange
        DestinationId destinationId = new DestinationId(UUID.randomUUID());
        Destination destination = new Destination(destinationId, TEST_URL);
        destinationRepository.registerDestination(destination);

        // Act
        destinationRepository.deleteDestination(destinationId);
    }

    @Test
    public void testLoadDestinationSuccess() throws Exception {
        // Arrange
        DestinationId destinationId = new DestinationId(UUID.randomUUID());
        Destination expected = new Destination(destinationId, TEST_URL);
        destinationRepository.registerDestination(expected);

        // Act
        Destination selected = destinationRepository.loadDestination(destinationId);

        // Assert
        assertEquals(expected.getId().getId(), selected.getId().getId());
        assertEquals(expected.getSecurity(), selected.getSecurity());
        assertEquals(expected.getUrl(), selected.getUrl());
    }
}