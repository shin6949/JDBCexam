package com.cocoblue.jdbcexam.dao;

import com.cocoblue.jdbcexam.dto.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    // 클래스 전반에서 사용할 DB 기본 정보 정의
    private static final String dbUrl = "jdbc:mysql://(DB 주소)/(DB 명)";
    private static final String dbUser = "DB 계정";
    private static final String dbPasswd = "DB 계정 비밀번호";

    // 지정한 검색어의 데이터를 갖고와 리턴해주는 코드
    public List<Person> searchPerson(String keyword) {
        // 검색 결과를 담을 리스트
        List<Person> people = new ArrayList<>();
        // DB Connection
        Connection conn = null;
        // 쿼리를 실행할 객체
        PreparedStatement ps = null;
        // 쿼리의 결과를 담을 객체
        ResultSet rs = null;

        // DB 작업은 인터넷 환경 등 예외 경우가 많아 try catch 사용이 필수
        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB에 연결
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
            // 구문 구성
            String selectQuery = "SELECT name, age, job, phone FROM personlist WHERE name LIKE ?";
            // 위에서 지정한 구문을 사용하겠다고 선언
            ps = conn.prepareStatement(selectQuery);
            // ?에 검색어를 넣어서 구문 완성
            ps.setString(1, keyword);
            // Query를 실행하고, 실행 결과를 rs에 저장
            rs = ps.executeQuery();

            // 결과 레코드가 끝날때까지 반복
            // rs.next() -> 다음 레코드가 없으면 False를 반환해서 while이 끝남.
            while(rs.next()) {
                // rs가 가르키는 현재 레코드에서 값을 찾아 변수에 저장
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String job = rs.getString("job");
                String phone = rs.getString("phone");

                // 리스트에 추가할 객체를 구성하고 리스트에 추가
                people.add(new Person(name, age, job, phone));
            } // while END
        // try END
        // 에러가 발생할 경우 catch를 실행
        } catch(Exception e) {
            // 에러를 추척하여 표시
            e.printStackTrace();
        // catch END
        // try 또는 catch가 끝나면 무조건 실행될 코드
        } finally {
            closeAll(ps, rs, conn);
        }

        // 구성된 객체를 반환
        return people;
    } // searchPerson END


    // 사람의 정보를 INSERT하는 코드
    public Boolean addPerson(Person person) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // JDBC 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            // DB에 연결
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
            // 구문 구성
            String insertQuery = "INSERT INTO personlist(name, age, job, phone) VALUES(?, ?, ?, ?)";
            // 위에서 지정한 구문을 사용하겠다고 선언
            ps = conn.prepareStatement(insertQuery);

            // ?에 검색어를 넣어서 구문 완성
            ps.setString(1, person.getName());
            ps.setInt(2, person.getAge());
            ps.setString(3, person.getJob());
            ps.setString(4, person.getPhone());

            // Query를 실행 -> executeUpdate()는 반영된 레코드의 수를 반환함. 즉, INSERT를 한번만 한 경우 정상적으로 되면 1을 반환하는 것이 맞음.
            int resultNum = ps.executeUpdate();

            // 반영된 레코드 수가 1개가 아닌 경우 제대로 된 것이 아님.
            if(resultNum != 1) {
                // 사용한 객체를 닫음.
                closeAll(ps, null, conn);
                // 제대로 되지 않았으니 결과로 false 반환
                return false;
            } // if END
        // try END
        // 에러가 발생하면 catch를 실행
        } catch(Exception e) {
            // 에러 내용을 추적하여 출력
            e.printStackTrace();
        // catch END
        // try 또는 catch가 실행된 후 실행되는 코드
        } finally {
            closeAll(ps, null, conn);
        }

        // 정상적으로 완료되었으니 true를 반환
        return true;
    } // addPerson END

    // 생성되었던 SQL 관련 객체들을 닫는 함수
    private void closeAll(PreparedStatement ps, ResultSet rs, Connection conn) {
        // rs가 null이 아닐 때
        if(rs != null) {
            try {
                // rs를 닫음
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // try, catch END
        } // if END

        // ps가 null이 아닐 때
        if(ps != null) {
            try {
                // ps를 닫음
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // try, catch END
        } // if END

        // conn가 null이 아닐 때
        if(conn != null) {
            try {
                // conn을 닫음.
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } // try, catch END
        } // if END
    }
}
