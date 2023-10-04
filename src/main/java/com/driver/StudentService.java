package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

     StudentRepository obj = new StudentRepository();
    public void addStudent(Student student){
        obj.addStuToDB(student);
    }

    public void addTeacher(Teacher teacher){
        obj.addTeachToDB(teacher);
    }

    public void addPair(String student, String teacher){
        Optional<Student> st = obj.getStudent(student);
        Optional<Teacher> teach = obj.getTeacher(teacher);
        if(st.isEmpty()){
            throw new RuntimeException("Student is not present in database");
        }
        if(teach.isEmpty()){
            throw new RuntimeException("teacher is not present in database");
        }
        Teacher teacherObj = teach.get();
        teacherObj.setNumberOfStudents(teacherObj.getNumberOfStudents()+1);
        obj.addTeachToDB(teacherObj);
        obj.addStuAndTeachPair(student, teacher);
    }

    public Student StudentByName(String name){
        Optional<Student> StOp = obj.getStudent(name);
        if(StOp.isPresent()){
            return StOp.get();
        }
        throw new RuntimeException("Student is no present");
    }

    public Teacher TeacherByName(String name){
        Optional<Teacher> TeachOp = obj.getTeacher(name);
        if(TeachOp.isPresent()){
            return TeachOp.get();

        }
        throw new RuntimeException("teacher is not present");
    }


    public List<String> getStudentByTeacherName(String teacher){
        return obj.getStudentByTeacherName(teacher);
    }

    public List<String> getAllStudent(){
        return obj.getAllStudent();
    }

    public void deleteTeacherByName(String teacher){
        List<String> st = getStudentByTeacherName(teacher);
        obj.deleteTeacher(teacher);
        for(String std:st){
            obj.deleteStudent(std);
        }

    }
    public void deleteAllTeacher(){
        List<String> teacher = obj.getAllTeacher();
        for(String tech :teacher){
            deleteTeacherByName(tech);
        }
    }
}
