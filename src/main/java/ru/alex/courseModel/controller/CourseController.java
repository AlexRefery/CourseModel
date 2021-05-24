package ru.alex.courseModel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.alex.courseModel.entity.Course;
import ru.alex.courseModel.entity.Student;
import ru.alex.courseModel.model.CourseDto;
import ru.alex.courseModel.model.StudentDto;
import ru.alex.courseModel.service.CourseService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<CourseDto> getCourses() {
        CourseDto courseDto = new CourseDto();
        return courseDto.getCourseDtoList(courseService.getAll());
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/del-{id}")
    public long deleteCourse(@PathVariable long id){
        courseService.deleteCourse(id);
        return id;
    }

    @PutMapping("/update-{id}")
    public Course updateCourse(@PathVariable long id, @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }
}
