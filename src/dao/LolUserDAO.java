package dao;

import util.Common;
import vo.LolUserVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LolUserDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL문을 수행하기 위한 객체
    ResultSet rs = null;    // statement 동작에 대한 결과로 전달되는 DB의 내용

    Scanner sc = new Scanner(System.in);
    public List<LolUserVO> userInfoSelect() {
        List<LolUserVO> list = new ArrayList<>(); // 반환할 리스트를 위해 리스트 객체 생성
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

                LolUserVO vo = new LolUserVO(id, pwd, email, nickname, birth_day, ph_number, gold, buyGold, rank);
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

    public void userSelectPrint(List<LolUserVO> list) {
        for (LolUserVO e : list) {
            System.out.println("아이디 : " + e.getId());
            //System.out.println("비밀번호 : " + e.getpwd());
            System.out.println("이메일 : " + e.getEmail());
            System.out.println("닉네임 : " + e.getNickname());
            System.out.println("생년월일 : " + e.getBirth_day());
            System.out.println("전화번호 : " + e.getPh_number());
            System.out.println("보유정수 : " + e.getGold());
            System.out.println("구매액 : " + e.getBuy_gold());
            System.out.println("랭크 : " + e.getRank());
            System.out.println("-----------------------------");

        }
    }

    public void userInfoInsert() {
        System.out.println("회원가입 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        String id = sc.next();
        System.out.print("비밀번호 : ");
        int pwd = sc.nextInt();
        System.out.print("이메일 : ");
        String email = sc.next();
        System.out.print("닉네임 : ");
        String nickname = sc.next();
        System.out.print("생년월일 : ");
        String birth_day = sc.next();
        System.out.print("전화번호 : ");
        int ph_number = sc.nextInt();

        String sql = "INSERT INTO USER_INFO (ID, PWD, EMAIL, NICKNAME, BIRTH_DAY, PH_NUMBER, GOLD, BUY_GOLD, RANK) VALUES ("
                + "'" + id + "'" + "," + "'" + pwd + "'" + "," + "'" + email + "'" + "," + "'" + nickname + "'" + ","
                + "'" + birth_day + "'" + "," + ph_number + ", '','','')";

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
}