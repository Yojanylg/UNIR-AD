package model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.io.Serializable;

public class Alumno implements Serializable {

    // private static final long serialVersionUID = 123123L;

    @BsonProperty("rating")
    private int rating;
    @BsonProperty("age")
    private int age;
    @BsonProperty("name")
    private String name;
    @BsonProperty("gender")
    private String gender;
    @BsonProperty("email")
    private String email;
    @BsonProperty("phone")
    private String phone;
    @BsonProperty("calification")
    private int calification;
    @BsonProperty("higher_grade")
    private String higher_grade;

    public Alumno() {
    }

    public Alumno(int rating, int age, String name, String gender, String email, String phone, int calification, String higher_grade) {
        this.rating = rating;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.calification = calification;
        this.higher_grade = higher_grade;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    public String getHigher_grade() {
        return higher_grade;
    }

    public void setHigher_grade(String higher_grade) {
        this.higher_grade = higher_grade;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "rating='" + rating + '\'' +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", calification='" + calification + '\'' +
                ", higher_grade='" + higher_grade + '\'' +
                '}';
    }
}
