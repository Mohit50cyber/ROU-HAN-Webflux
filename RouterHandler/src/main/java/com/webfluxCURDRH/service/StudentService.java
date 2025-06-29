package com.webfluxCURDRH.service;

import com.webfluxCURDRH.dto.StudentDTO;
import com.webfluxCURDRH.mapper.StudentMapper;
import com.webfluxCURDRH.model.Student;
import com.webfluxCURDRH.repo.StudentRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;

    public StudentService (StudentRepo studentRepo , StudentMapper studentMapper){
        this.studentRepo=studentRepo;
        this.studentMapper=studentMapper;
    }

    public Mono<StudentDTO> createStudent(StudentDTO studentDTO){
        Student student = studentMapper.toEntity(studentDTO);
        return studentRepo.save(student)
                .map(studentMapper::toDTO);
    }

    public Flux<StudentDTO> getAllStudents(){
        return studentRepo.findAll()
                .map((studentMapper::toDTO));
    }

    public Mono<StudentDTO> getStudentById(String id){
        return studentRepo.findById(id)
                .map(studentMapper::toDTO);
    }

    public Mono<StudentDTO> updateStudent(String id, StudentDTO studentDTO){
        return studentRepo.findById(id)
                .flatMap(existingStudent->{
                    existingStudent.setName(studentDTO.getName());
                    existingStudent.setAge(studentDTO.getAge());
                    return studentRepo.save(existingStudent);
                })
                .map(studentMapper::toDTO);
    }

    public Mono<Void> deleteStudent(String id){

        return studentRepo.deleteById(id);
    }
























}
