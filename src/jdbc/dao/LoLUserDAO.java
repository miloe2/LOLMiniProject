package jdbc.dao;

import jdbc.util.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoLUserDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL문을 수행하기 위한 객체
    ResultSet rs = null;    // statement 동작에 대한 결과로 전달되는 DB의 내용



    public void userInfoInsert () {
        Scanner sc = new Scanner(System.in);
        System.out.println("회원가입 정보를 입력해주세요.");
        String pwPattern = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z])";


        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql1 = "SELECT ID FROM USER_INFO";
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                System.out.print("아이디 : ");
                String id = sc.next();
                if (!id.equals(rs.getString("ID"))) {
                    System.out.print("비밀번호 : ");
                    String pwd = sc.next();
                    Matcher matcher = Pattern.compile(pwPattern).matcher(pwd);
                    while(!matcher.matches()){
                        System.out.println("비밀번호를 다시 입력해주세요");
                    }
                    System.out.print("이메일 : ");
                    String email = sc.next();
                    System.out.print("닉네임 : ");
                    String nickName = sc.next();
                    System.out.print("생년월일 : ");
                    String birthDay = sc.next();
                    System.out.print("전화번호 : ");
                    int phNumber = sc.nextInt();

                    String sql = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER) VALUES ("
                            + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickName + "'" + ","
                            + "'" + birthDay + "'" + "," + phNumber + ")";

                    rs = stmt.executeQuery(sql);
                    System.out.println("회원가입 완료");
                    return;

                } else System.out.println("이미 사용중인 아이디입니다. ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
}