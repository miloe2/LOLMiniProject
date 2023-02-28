package dao;

import util.Common;
import vo.MemberVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoLUserDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL문을 수행하기 위한 객체
    ResultSet rs = null;    // statement 동작에 대한 결과로 전달되는 DB의 내용

    Scanner sc = new Scanner(System.in);

    public List<MemberVO> userInfoSelect() {
        List<MemberVO> list = new ArrayList<>(); // 반환할 리스트를 위해 리스트 객체 생성
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM USER_INFO";
            rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용

            while (rs.next()) { // 읽을 행이 있으면 참
                String id = rs.getString("ID");
                String pwd = rs.getString("PWD");
                String email = rs.getString("EMAIL");
                String nickname = rs.getString("NICKNAME");
                Date birth_day = rs.getDate("BIRTH_DAY");
                int ph_number = rs.getInt("PH_NUMBER");
                int gold = rs.getInt("GOLD");
                int buyGold = rs.getInt("BUY_GOLD");
                String rank = rs.getString("RANK");

                MemberVO vo = new MemberVO(id, pwd, email, nickname, birth_day, ph_number, gold, buyGold, rank);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void userSelectPrint(List<MemberVO> list) {
        for (MemberVO e : list) {
            System.out.println("아이디 : " + e.getId());
            //System.out.println("비밀번호 : " + e.getpwd());
            System.out.println("이메일 : " + e.getEmail());
            System.out.println("닉네임 : " + e.getNickName());
            System.out.println("생년월일 : " + e.getBirthDay());
            System.out.println("전화번호 : " + e.getPhNumber());
            System.out.println("보유정수 : " + e.getGold());
            System.out.println("구매액 : " + e.getBuyGold());
            System.out.println("랭크 : " + e.getRank());
            System.out.println("-----------------------------");

        }
    }
    public void userInfoInsert1() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("회원가입 정보를 입력해주세요.");
        String sql = "SELECT ID FROM USER_INFO";
        rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)으로 결과가 반환될 때 사용
        while (rs.next()) {
            System.out.print("아이디를 입력하세요 : ");
            String id = sc.next();
            if (id.equals(rs.getString("ID"))) {
                System.out.println("이미 사용중인 아이디입니다. ");
                continue;
            } else {
                System.out.print("비밀번호 : ");
                int pwd = sc.nextInt();
                System.out.print("이메일 : ");
                String email = sc.next();
                System.out.print("닉네임 : ");
                String nickName = sc.next();
                System.out.print("생년월일 : ");
                String birthDay = sc.next();
                System.out.print("전화번호 : ");
                int phNumber = sc.nextInt();

                String sql1 = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) VALUES ("
                        + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickName + "'" + ","
                        + "'" + birthDay + "'" + "," + phNumber + ", '','','')";

                try {
                    conn = Common.getConnection();
                    stmt = conn.createStatement();
                    int ret = stmt.executeUpdate(sql1);
                    System.out.println("Return : " + ret);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Common.close(stmt);
                Common.close(conn);

            }
        }
    }



    public void userInfoInsert() {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT ID FROM USER_INFO";
            rs = stmt.executeQuery(sql); // select 문과 같이 여러개의 레코드(행)으로 결과가 반환될 때 사용
            System.out.println("회원가입 정보를 입력해주세요.");
            while (rs.next()) {
                System.out.print("아이디를 입력하세요 : ");
                String id = sc.next();
                if (id.equals(rs.getString("ID"))) {
                    System.out.println("이미 사용중인 아이디입니다. ");
                    continue;
                } else {
                    System.out.print("비밀번호 : ");
                    int pwd = sc.nextInt();
                    System.out.print("이메일 : ");
                    String email = sc.next();
                    System.out.print("닉네임 : ");
                    String nickName = sc.next();
                    System.out.print("생년월일 : ");
                    String birthDay = sc.next();
                    System.out.print("전화번호 : ");
                    int phNumber = sc.nextInt();

                    String sql1 = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) VALUES ("
                            + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickName + "'" + ","
                            + "'" + birthDay + "'" + "," + phNumber + ", '','','')";
                }
                try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);

        } catch (Exception e) {
            e.printStackTrace();
        }

            }
            Common.close(rs); // 연결의 역순 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//    public void userInfoInsert(List<MemberVO> list) {
//        while(true)
//
//        System.out.println("회원가입 정보를 입력해주세요.");
//        System.out.print("아이디 : ");
//        String id = sc.next();
//        System.out.print("비밀번호 : ");
//        String pwd = sc.next();
//        System.out.print("이메일 : ");
//        String email = sc.next();
//        System.out.print("닉네임 : ");
//        String nickName = sc.next();
//        System.out.print("생년월일 : ");
//        String birthDay = sc.next();
//        System.out.print("전화번호 : ");
//        int phNumber = sc.nextInt();
//
//
//        String sql = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) VALUES ("
//                + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickName + "'" + ","
//                + "'" + birthDay + "'" + "," + phNumber + ", '','','')";
//
//
//        try {
//            conn = Common.getConnection();
//            stmt = conn.createStatement();
//            int ret = stmt.executeUpdate(sql);
//            System.out.println("Return : " + ret);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Common.close(stmt);
//        Common.close(conn);
//    }
//}
