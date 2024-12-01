package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.MongoConnection;
import model.Profesor;
import org.bson.Document;

import java.util.ArrayList;

public class ProfesorDAO {

    MongoCollection collectionProfesores;

    public ProfesorDAO(){
        collectionProfesores = new MongoConnection().getProfesoresCollection();
    }

    public void insertarProfesor(Profesor profesor){
        Document document = new Document().append("rating", profesor.getRating())
                .append("age", profesor.getAge())
                .append("name", profesor.getName())
                .append("gender", profesor.getGender())
                .append("email", profesor.getEmail())
                .append("phone", profesor.getPhone())
                .append("subjects", profesor.getSubjects())
                .append("title", profesor.getTitle());

        collectionProfesores.insertOne(document);
    }

    public ArrayList<Profesor> getProfesores(){
        FindIterable iterable = collectionProfesores.find();
        MongoCursor<Document> cursor = iterable.cursor();
        ArrayList<Profesor> listaProfesores = new ArrayList<>();

        while (cursor.hasNext()){
            Document document = cursor.next();

            int rating = document.getInteger("rating");
            int age = document.getInteger("age");
            String name = document.getString("name");
            String gender = document.getString("gender");
            String email = document.getString("email");
            String phone = document.getString("phone");
            String subjects = document.getString("subjects");
            String tittle = document.getString("title");

            listaProfesores.add(new Profesor(rating, age,name,gender,email,phone,subjects,tittle));
        }

        return listaProfesores;

    }


}
