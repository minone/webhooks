package br.com.minone.webhooks.infrastructure.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

@Repository
public class FirebaseRepository {

    public FirebaseRepository() {

        FirebaseOptions options = null;

        try {

            ClassLoader classLoader = getClass().getClassLoader();

            File file = new File(classLoader.getResource("webhooks-190db004a295.json").getFile());

            FileInputStream fis = new FileInputStream(file);

            options = new FirebaseOptions.Builder()
                    .setServiceAccount(fis)
                    .setDatabaseUrl("https://webhooks-458d0.firebaseio.com")
                    .build();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
    }


    public void post(String message) {
        System.out.println(message);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("log");

        ref.child(UUID.randomUUID().toString()).setValue(new LogEntry(message));

    }
}
