package com.cocoblue.jdbcexam.ui;

import com.cocoblue.jdbcexam.dao.PersonDao;
import com.cocoblue.jdbcexam.dto.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDao();
        List<Person> people = personDao.searchPerson("홍길동");

        // 리스트의 끝까지 반복
        for(Person person: people) {
            System.out.println(person);
        }

        personDao.addPerson(new Person("신상민", 23, "대학생", "010-1234-8888"));
        System.out.println("정상적으로 INSERT 됨.");
    }
}
