import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Main {

    private static final String PROJECT_ID = "brep-playground";
    private static final String FILE_PATH = "src/main/resources/brep-playground-4fc0d2147989.json";

    public static void main(String[] args) throws IOException {
        DatastoreOptions options = DatastoreOptions.newBuilder()
                .setProjectId(PROJECT_ID)
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(FILE_PATH)))
                .build();
        Datastore datastore = options.getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("brep_manual_sitek");
        Key key = keyFactory.newKey(new Random().nextLong());
        Entity entity = Entity.newBuilder(key)
                .set("date_string", new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()))
                .build();
        datastore.put(entity);
        System.out.println("Success! Entity has been saved to datastore");
    }

}
