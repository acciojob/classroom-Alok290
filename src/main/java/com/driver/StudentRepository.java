package com.driver;

import org.springframework.stereotype.Repository;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    HashMap<String,Student> StDb = new HashMap<>();
    HashMap<String,Teacher> TeacherDb = new HashMap<>();
    HashMap<String , ArrayList<String>> PairDb = new HashMap<>();


    public void addStuToDB(Student student){
        //to adding student in student  database

        StDb.put(student.getName(), student);
    }

    public void addTeachToDB(Teacher teacher){
//        to adding teacher in teacher database
        TeacherDb.put(teacher.getName(),teacher);
    }

public void addStuAndTeachPair(String student, String teacher){
        //adding student and teacher in pair

    ArrayList<String> st=PairDb.getOrDefault(teacher,new ArrayList<String>());
    st.add(student);
    PairDb.put(teacher,st);
}

    public Optional<Student> getStudent(String student){
        if(StDb.containsKey(student)){
            return Optional.of(StDb.get(student));
        }
        return Optional.empty();

    }
    public Optional<Teacher> getTeacher(String teacher){
        if(TeacherDb.containsKey(teacher)){
            return Optional.of(TeacherDb.get(teacher));
        }
        return Optional.empty();
    }

    public List<String>getStudentByTeacherName(String teacher){
        return PairDb.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> getAllStudent(){
        return new ArrayList<>(StDb.keySet());
    }

    public void deleteTeacher(String teacher){
        TeacherDb.remove(teacher);
        PairDb.remove(teacher);
    }


    public void deleteStudent(String student){
        StDb.remove(student);

    }
    public List<String> getAllTeacher(){
        return new ArrayList<>(TeacherDb.keySet());
    }

}
