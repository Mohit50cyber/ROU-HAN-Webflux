package com.webfluxCURDRH.handler;

import com.webfluxCURDRH.dto.StudentDTO;
import com.webfluxCURDRH.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

    private final StudentService studentService;

    public StudentHandler(StudentService studentService){

        this.studentService=studentService;
    }

    public Mono<ServerResponse> createStudent(ServerRequest request){
        return request.bodyToMono(StudentDTO.class)
                .flatMap(studentService::createStudent)
                .flatMap(studentDTO -> ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(studentDTO));
    }

    public Mono<ServerResponse> getAllStudent(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentService.getAllStudents(),StudentDTO.class);
    }

    public Mono<ServerResponse> getStudentById(ServerRequest request){
        String id = request.pathVariable("id");
        return studentService.getStudentById(id)
                .flatMap(studentDTO -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(studentDTO))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> updateStudent(ServerRequest request){
        String id = request.pathVariable("id");
        return request.bodyToMono(StudentDTO.class)
                .flatMap(studentDTO -> studentService.updateStudent(id,studentDTO))
                .flatMap(updateStudentDTO->ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(updateStudentDTO))
                .switchIfEmpty(ServerResponse.notFound().build());
    }


    public Mono<ServerResponse> deleteStudent(ServerRequest request){
        String id = request.pathVariable("id");
        return studentService.deleteStudent(id)
                .then(ServerResponse.noContent().build());
    }














}
