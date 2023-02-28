package com.spring.app.controller;

import com.spring.app.entity.Student;
import com.spring.app.services.StudentService;
import com.spring.app.utils.SerializeUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
public class HomeController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/home")
    public String home(Model model){
        List<Student> list = studentService.getAllStudent();
        model.addAttribute("students",list);
        return "index";
    }

    @RequestMapping(value = "/send",method = RequestMethod.GET)
    public ResponseEntity<String> getData(@RequestHeader(name = "Authorization") String header,@RequestBody String name){
        if(header == null || header.length()==0)return ResponseEntity.badRequest().body("Authorization must be passed with the request");
        System.out.println(header);
        System.out.println(name);
        return ResponseEntity.ok(name);
    }

    @RequestMapping("/add")
    public String add(){
        return "insert";
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleClick(@PathVariable int id) throws IOException, IllegalAccessException {
        System.out.println("handleClick called");
        System.out.println(id);
        Student student = new Student(1,"Pranav","BTECH/60077/19");
//        return "redirect:/home";
        return ResponseEntity.ok("Ok ji ok");
    }

    @PostMapping("/insertStudent")
    public String insert(@ModelAttribute Student student,Model model){
        int result = studentService.insertStudentRecord(student);
        if(result<=0)return "redirect:add";
        return "redirect:home";
    }
}
