package com.cocoblue.jdbcexam.dao;

import com.cocoblue.jdbcexam.dto.Person;
import com.cocoblue.jdbcexam.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    public List<Person> searchPerson(String keyword) {
        List<Person> people = new ArrayList<>();

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT name, age, job, phone FROM personlist WHERE name LIKE ?");
            ) {
            ps.setString(1, keyword);

            try (ResultSet rs = ps.executeQuery()) {
                // 결과 레코드가 끝날때까지 반복
                while(rs.next()) {
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    String job = rs.getString("job");
                    String phone = rs.getString("phone");

                    people.add(new Person(name, age, job, phone));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return people;
    }

    public void addPerson(Person person) {
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement("INSERT INTO personlist(name, age, job, phone) VALUES(?, ?, ?, ?)")
        ) {
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getJob());
            ps.setString(4, person.getPhone());
            // 구문 실행
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
