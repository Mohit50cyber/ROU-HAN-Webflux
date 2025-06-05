package com.webfluxCURDRH.router;

import com.webfluxCURDRH.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> route(StudentHandler handler){
        return RouterFunctions
                .route(RequestPredicates.POST("/students").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::createStudent)
                .andRoute(RequestPredicates.GET("students").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::getAllStudent)
                .andRoute(RequestPredicates.GET("/students/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::getStudentById)
                .andRoute(RequestPredicates.PUT("students/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::updateStudent)
                .andRoute(RequestPredicates.DELETE("/students/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::deleteStudent);
    }
}
