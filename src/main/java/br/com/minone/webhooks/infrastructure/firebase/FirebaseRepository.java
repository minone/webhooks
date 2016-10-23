package br.com.minone.webhooks.infrastructure.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Repository
public class FirebaseRepository {

    public FirebaseRepository() {
    }


    public void post() {
        String projectId = "webhooks-458d0";

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setServiceAccount(new FileInputStream("path/to/serviceAccountCredentials.json"))
                    .setDatabaseUrl("https://webhooks-458d0.firebaseio.com")
                    .build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
    }
}
