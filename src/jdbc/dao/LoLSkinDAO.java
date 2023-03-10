package jdbc.dao;

import jdbc.JdbcMain;
import jdbc.util.Common;
import jdbc.vo.LoLSkinVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoLSkinDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // SQL 문을 수행하기 위한 객체
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    Scanner sc = new Scanner(System.in);
    PreparedStatement pstmt = null;

    public List<LoLSkinVO> LoLSkinSelect() {
        JdbcMain main = new JdbcMain();
        String currentID = main.getLoginSuccess();
        List<LoLSkinVO> SKlist = new ArrayList<>();
        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String sql = "SELECT CHP_NAME FROM CHAMPION_BUY " +
                    "WHERE ID = '" + currentID+"'";
            rs = stmt.executeQuery(sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용
            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_name = rs.getString("CHP_NAME");
                LoLSkinVO vo = new LoLSkinVO(chp_name); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                SKlist.add(vo); // 리스트에 추가
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKlist;
    }

    public void LoLSelectPrint(List<LoLSkinVO> list) {
        for (LoLSkinVO e : list) {
            System.out.println("챔피언 이름 : " + e.getChp_name());
            System.out.println("---------------------------------");
        }
    }

    public List<LoLSkinVO> LoLSkinBuySelect() {
        List<LoLSkinVO> list2 = new ArrayList<>(); // 반환한 리스트를 위해 리스트 객체 생성
        System.out.println("구입할 스킨의 챔피언 이름을 입력해주세요 : ");
        String CHPname = sc.next();
        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String sql = "SELECT * FROM SKIN WHERE CHP_NAME = " + "'" + CHPname + "'";
            rs = stmt.executeQuery(sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용
            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_name = rs.getString("CHP_NAME");
                String sk_name = rs.getString("SK_NAME");
                int sk_price = rs.getInt("SK_PRICE");
                LoLSkinVO vo = new LoLSkinVO(chp_name, sk_name, sk_price); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list2.add(vo); // 리스트에 추가
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2;
    }

    public void LoLSKinBuySelectPrint(List<LoLSkinVO> list2) {
        for (LoLSkinVO e : list2) {
            System.out.println("챔피언 이름 : " + e.getChp_name());
            System.out.println("스킨 이름 : " + e.getSk_name());
            System.out.println("스킨 가격 : " + e.getSk_price());
            System.out.println("---------------------------------");
        }
    }

    public void LoLSkinInsert() {
        JdbcMain main = new JdbcMain();
        String currentID = main.getLoginSuccess();
        List<LoLSkinVO> list3 = new ArrayList<>();
        System.out.println("구매하실 스킨 이름을 입력해 주세요 : ");
        sc.nextLine();
        String SkinBuyName = sc.nextLine();
        try {
            conn = Common.getConnection(); // 연결을 가져옴
            stmt = conn.createStatement(); // stmt 에 쿼리문을 담아서 가져옴
            String buy_sql = "SELECT * FROM SKIN WHERE SK_NAME = '" + SkinBuyName + "'";
            rs = stmt.executeQuery(buy_sql); // rs에 쿼리문의 값이 담김, select 문과 같이 여러개의 레코드(행)로 결과가 반환될 때 사용

            while (rs.next()) { // 읽을 행이 있으면 참
                String chp_name = rs.getString("CHP_NAME");
                String sk_name = rs.getString("SK_NAME");
                int sk_price = rs.getInt("SK_PRICE");
                LoLSkinVO vo1 = new LoLSkinVO(chp_name, sk_name, sk_price); // 하나의 행(레코드)에 대한 정보 저장을 위한 객체 생성
                list3.add(vo1); // 리스트에 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO SKIN_BUY(SKNO,ID,CHP_NAME,SK_NAME,SK_PRICE) VALUES(S_SKSEQ.NEXTVAL,?,?,?,?)";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (LoLSkinVO e : list3) {
                pstmt.setString(1, currentID);
                pstmt.setString(2, e.getChp_name());
                pstmt.setString(3, e.getSk_name());
                pstmt.setInt(4, e.getSk_price());
            }

            pstmt.executeUpdate();
            System.out.println(SkinBuyName + "을 구입 완료 했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String glod_sql = "UPDATE USER_INFO SET GOLD = GOLD - ?, BUY_GOLD = NVL(BUY_GOLD,0) + ? WHERE ID = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(glod_sql);
            for (LoLSkinVO e : list3) {
                pstmt.setInt(1, e.getSk_price());
                pstmt.setInt(2, e.getSk_price());
                pstmt.setString(3, currentID);
            }
            pstmt.executeUpdate();
            System.out.println("회원정보 업데이트 완료");
        } catch (Exception e) {
            e.printStackTrace();

            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
            Common.close(pstmt);

        }
    }
}