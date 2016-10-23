package br.com.minone.webhooks.infrastructure.firebase;

import org.junit.Test;

public class FirebaseRepositoryTest {

    @Test
    public void testPost() throws Exception {

        FirebaseRepository firebaseRepository = new FirebaseRepository();

        firebaseRepository.post();
    }
}