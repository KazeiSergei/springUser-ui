package com.mkyong.web.controller;

import com.courses.spring.dao.interfaces.MarkDao;
import com.courses.spring.dao.interfaces.StudentDao;
import com.courses.spring.dao.interfaces.SubjectDao;
import com.courses.spring.dao.objects.Mark;
import com.courses.spring.dao.objects.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class MainController {


    @Autowired
    private StudentDao jdbcStudentDao;
    @Autowired
    private MarkDao jdbcMarkDao;
    @Autowired
    private SubjectDao jdbcSubjectDao;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printStudents() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("students", jdbcStudentDao.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/getStudentById", method = RequestMethod.GET)
    public ModelAndView getStudentById(@RequestParam("id") Integer id) {

        Student student = jdbcStudentDao.getStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("student");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createStudent() {
        return new ModelAndView("createStudent", "student", new Student());
    }

    @RequestMapping(value = "/createStudent", method = RequestMethod.POST)
    public String addStudentToDB(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "createStudent";
        }
        jdbcStudentDao.insertStudent(student);

        return "redirect:/";
    }


    @RequestMapping(value = "/pageForUpdating", method = RequestMethod.GET)
    public ModelAndView pageForUpdating(@RequestParam("id") Integer id) {
        Student student = jdbcStudentDao.getStudentById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("student", student);
        modelAndView.setViewName("pageForUpdating");
        return modelAndView;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public String pageForUpdating(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "pageForUpdating";
        }

        jdbcStudentDao.updateStudent(student);

        return "redirect:/getStudentById?id=" + student.getId();
    }


    @RequestMapping(value = "/deleteStudent", method = RequestMethod.GET)
    public String deleteStudent(@RequestParam("id") Integer id) {
        jdbcStudentDao.delateStudent(id);
        return "redirect:/";
    }


    @RequestMapping(value = "/getStudentWithMarkAndSubject", method = RequestMethod.GET)
    public ModelAndView getStudentWithMarkAndSudject(@RequestParam("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", jdbcStudentDao.getStudentWithMark(id));
        modelAndView.addObject("id", id);
        modelAndView.addObject("subjects", jdbcSubjectDao.getAllSudject());
        modelAndView.setViewName("getStudentWithMarkAndSubject");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteMark/{id}", method = RequestMethod.POST)
    public String delateMark(@PathVariable("id") int id, HttpServletRequest request) {
        String[] marksId = request.getParameterValues("markId");
        for (String markId : marksId) {
            jdbcMarkDao.delateMark(Integer.parseInt(markId));
        }
        return "redirect:/getStudentWithMarkAndSubject?id=" + id;
    }

    @RequestMapping(value = "/insertMark/{id}", method = RequestMethod.POST)
    public String insertMark(@PathVariable("id") int id, HttpServletRequest request) {

        int subjectId = Integer.parseInt(request.getParameter("subject"));
        int mark = Integer.parseInt(request.getParameter("mark"));
        jdbcMarkDao.insertMark(new Mark(id, subjectId, mark));
        return "redirect:/getStudentWithMarkAndSubject?id=" + id;
    }


}