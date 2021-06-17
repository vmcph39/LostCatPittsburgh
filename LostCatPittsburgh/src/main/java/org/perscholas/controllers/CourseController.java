package org.perscholas.controllers;

import lombok.extern.java.Log;
import org.perscholas.models.Course;
import org.perscholas.models.Student;
import org.perscholas.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("course")
@Log
@SessionAttributes({"course"})
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    /*
            - controllers should be separated e.g. @RequestMapping("admin"), @RequestMapping("student")
            - provide as much as possible e.g. get/post/put/delete mappings
     */

    @GetMapping("/showcourses")
    public String newCourse(@ModelAttribute("course") @Valid Course course, BindingResult result, Model model ){
        System.out.println(result.hasErrors());
        if(!result.hasErrors()) {
            List<Course> c = courseService.getCourses();
            model.addAttribute("courseTemp", c);
            System.out.println(c);
        }
        return "displaycourses";
    }

    @ModelAttribute("course")
    public Course initCourse(){
        return new Course();
    }

    @GetMapping("/registercourse")
    public String register(){
        return "registertocourse";
    }

    @PostMapping("/submit")
    public String registerCourse(@ModelAttribute("course")@Valid Course course, BindingResult result, Model model){

            Course c = courseService.addNewCourse(course);
            model.addAttribute("courseTemp", c);
            System.out.println(c);
            return "displaycourses";

    }
}
