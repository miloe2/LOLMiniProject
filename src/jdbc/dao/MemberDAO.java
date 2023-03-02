package jdbc.dao;

import jdbc.util.Common;
import jdbc.vo.MemberVO;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberDAO {
    public String getCurrentID() {
        return currentID;
    }

    public void setCurrentID(String currentID) {
        this.currentID = currentID;
    }

    private String currentID;
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // sql문을 수행하기 위한 객체
    ResultSet rs = null;  // statement 동작에 대한 결과로 전달되는 db의 내용
    Scanner sc = new Scanner(System.in);

    public List<MemberVO> memberSelect() {
        List<MemberVO> list = new ArrayList<>(); // 반환할 리스트를 위해 객체 생성
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USER_INFO";
            rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)으로 결과가 반환될 때 사용

            while (rs.next()) { // 읽을 행이 있으면 참
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                String email = rs.getString("EMAIL");
                String nickName = rs.getString("NICKNAME");
                Date birthDay = rs.getDate("BIRTH_DAY");
                int PhNumber = rs.getInt("PH_NUMBER");
                int gold = rs.getInt("GOLD");
                int buyGold = rs.getInt("BUY_GOLD");
                String rank = rs.getString("RANK");
                MemberVO vo = new MemberVO(id, pwd, email, nickName, birthDay, PhNumber, gold, buyGold, rank); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list.add(vo);
            }
            Common.close(rs); // 연결의 역순 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String lolLogin(List<MemberVO> list) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT ID,PWD FROM USER_INFO";
            rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)으로 결과가 반환될 때 사용
            System.out.print("아이디를 입력하세요 : ");
            String id = sc.next();
            System.out.print("비밀번호를 입력하세요 : ");
            String pwd = sc.next();
            while (rs.next()) {
                if (id.equals(rs.getString("ID"))) {
                    if (pwd.equals(rs.getString("PWD"))) {
                        return id;
                    }
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "실패";
    }

    public void userInfoInsert() {

        Scanner sc = new Scanner(System.in);
        System.out.println("회원가입 정보를 입력해주세요.");
        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql1 = "SELECT ID FROM USER_INFO";
            rs = stmt.executeQuery(sql1);
            while (rs.next()) {
                System.out.print("아이디 : ");
                String id = sc.next();
                if (!id.equals(rs.getString("ID"))) {
                    while (true) {
                        System.out.print("비밀번호 : ");
                        String pwd = sc.next();
                        Matcher passMatcher1 = passPattern1.matcher(pwd);
                        if (!passMatcher1.find()) {
                            System.out.println("비밀번호는 영문과 특수문자 숫자를 포함하며 8자 이상이어야 합니다.");
                            continue;
                        }
                        System.out.print("이메일 : ");
                        String email = sc.next();
                        System.out.print("닉네임 : ");
                        String nickName = sc.next();

                        while (true){
                            System.out.print("생년월일 8자리 : ");
                            String birthDay = sc.next();
                            String birthYear = birthDay.substring(0,4);
                            int birth = Integer.parseInt(birthYear);

                            if (2023-birth <= 12) {
                                System.out.println("성인이 아닙니다. ");
                                continue;
                            }
                                System.out.print("전화번호 : ");
                                int phNumber = sc.nextInt();
                                String sql = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER) VALUES ("
                                        + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickName + "'" + ","
                                        + "'" + birthDay + "'" + "," + phNumber + ")";

                                rs = stmt.executeQuery(sql);
                                System.out.println("회원가입 완료");
                                return;
                        }
                    }
                } else System.out.println("이미 사용중인 아이디입니다. ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
}