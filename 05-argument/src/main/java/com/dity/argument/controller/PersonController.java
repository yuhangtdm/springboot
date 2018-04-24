package com.dity.argument.controller;

import com.dity.argument.annotation.MultiPerson;
import com.dity.argument.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/23
 */
@Controller
@RequestMapping("person")
public class PersonController {

    @RequestMapping("add")
    @ResponseBody
    public String addPerson(Person person){
        return person.getFirstName()+"-"+person.getLastName();
    }

    @RequestMapping("add2")
    @ResponseBody
    public  List<Person> add2Person(@MultiPerson("per1")  Person person1,@MultiPerson("per2") Person person2){
        List<Person> people=new ArrayList<>();
        people.add(person1);
        people.add(person2);
        return people;
    }

    @RequestMapping("detail")
    @ResponseBody
    public Person detail(){
        Person person=new Person("Bill","Gates");
        return person;
    }

    @RequestMapping("converter")

    public Person converter(@RequestBody Person person){
        return person;
    }
}
