package com.webfluxCURDRH.mapper;

import com.webfluxCURDRH.dto.StudentDTO;
import com.webfluxCURDRH.model.Student;

@org.mapstruct.Mapper
public interface StudentMapper {

    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO studentDTO);
}
