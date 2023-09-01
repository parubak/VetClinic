package com.example.vetclinic.service;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService extends UserDetailsService {
    void saveUserRegistration(UserDto userDto);


// # Creates a new Course
//    Iterable<Course> createCourse(Course course);
//    # Loads a course by the supplied courseID. The Optional return type indicates there might not
//    be a course available with the supplied id
//    Optional<Course> findCourseById(int courseId);
//    # Loads all available courses
//    Iterable<Course> findAllCourses();
//    # Update a course details
//    Iterable<Course> updateCourse(Course course);
//
//    # Deletes a course by the supplied courseId
//    Iterable<Course> deleteCourseById(int courseId);
    User findUserByPhoneNumber(String phoneNumber);

    User uploadUser(User user, MultipartFile multipartFile,String uploadPath) throws IOException;


    User findUserById(long id);

    String getPathPhoto();
}