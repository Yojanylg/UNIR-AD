package database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoConnection {

    private final MongoClient mongoClient;
    private final MongoDatabase database;

    private final MongoCollection<Document> alumnosCollection;
    private final MongoCollection<Document> profesoresCollection;

    public MongoConnection() {
        String connectionString = String.format(
                "mongodb+srv://%s:%s@cluster0.lgb98.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0",
                DBScheme.USER, DBScheme.PASS
        );

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        this.mongoClient = MongoClients.create(settings);
        this.database = mongoClient.getDatabase(DBScheme.DATABASE_CENTROESCOLAR);

        this.alumnosCollection = database.getCollection(DBScheme.COLLECTION_ALUMNOS);
        this.profesoresCollection = database.getCollection(DBScheme.COLLECTION_PROFESORES);
    }
    public MongoCollection getAlumnosCollection() {
        return alumnosCollection;
    }

    public MongoCollection getProfesoresCollection() {
        return profesoresCollection;
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
