package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import database.MongoConnection;
import model.Alumno;
import org.bson.Document;


import java.util.ArrayList;

public class AlumnoDAO {

    MongoCollection collectionAlumnos;

    public AlumnoDAO(){
        collectionAlumnos = new MongoConnection().getAlumnosCollection();
    }

    public void insertarAlumno(Alumno alumno){
        Document document = new Document().append("name", alumno.getName())
                .append("email", alumno.getEmail())
                .append("age", alumno.getAge())
                .append("rating", alumno.getRating())
                .append("gender", alumno.getGender())
                .append("phone", alumno.getPhone())
                .append("calification", alumno.getCalification())
                .append("higher_grade", alumno.getHigher_grade());
        collectionAlumnos.insertOne(document);
    }

    public ArrayList<Alumno> getAlumnos(){
        FindIterable iterable = collectionAlumnos.find();
        MongoCursor<Document> cursor = iterable.cursor();
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        while (cursor.hasNext()){
            Document document = cursor.next();

            int rating = document.getInteger("rating");
            int age = document.getInteger("age");
            String name = document.getString("name");
            String gender = document.getString("gender");
            String email = document.getString("email");
            String phone = document.getString("phone");
            int calification = document.getInteger("calification");
            String higher_grade = document.getString("higher_grade");

            listaAlumnos.add(new Alumno(rating,age,name,gender,email,phone,calification,higher_grade));
        }

        return listaAlumnos;

    }
}
