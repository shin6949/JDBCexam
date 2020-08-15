package com.cocoblue.jdbcexam.ui;

import com.cocoblue.jdbcexam.dao.PersonDao;
import com.cocoblue.jdbcexam.dto.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Dao 객체를 만듦.
        PersonDao personDao = new PersonDao();
        // 홍길동 키워드로 검색해서 결과를 받아옴.
        List<Person> people = personDao.searchPerson("홍길동");
        // 리스트의 끝까지 반복
        for(Person person: people) {
            // 받아온 정보를 출력
            System.out.println(person);
        }

        // INSERT 결과를 저장할 boolean
        boolean result = personDao.addPerson(new Person("신상민", 23, "대학생", "010-1234-8888"));
        if (result) {
            System.out.println("정상적으로 INSERT 됨.");
        // if END
        } else {
            System.out.println("정상적으로 되지 않음.");
        } // else END
    }
}
