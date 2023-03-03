package jdbc.dao;

import jdbc.JdbcMain;
import jdbc.util.Common;

import java.sql.*;

public class GameDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null; // SQL 문을 수행하기 위한 객체
    PreparedStatement pstmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public void WinLose() {
        JdbcMain main = new JdbcMain();
        String currentID = main.getLoginSuccess();
        System.out.println("게임이 시작되었습니다. ");

        try {
            Connection conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT POINT FROM USER_INFO WHERE ID = '" + currentID + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) { // 읽을 행이 있으면 참
                int score = rs.getInt("POINT");

                if ((int) (Math.random() * 2) == 0) {
                    score += 20;
                    String sql1 = "UPDATE USER_INFO SET POINT =" + score + " WHERE ID = '" + currentID + "'";
                    rs = stmt.executeQuery(sql1);
                    System.out.println("승리");
                    if (score == 2800) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'CHALLENGER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("챌린저로 승급하셨니다. ");
                        System.out.println("현재 랭크 : 챌린저 " +  score);
                    } else if (score == 2150) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("다이아몬드로 승급하셨습니다.  ");
                        System.out.println("현재 랭크 : 다이아몬드 " +  score);
                    } else if (score == 1850) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("플래티넘으로 승급하셨습니다.  ");
                        System.out.println("현재 랭크 : 플래티넘 " +  score);
                    } else if (score == 1500) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("골드로 승급하셨습니다.  ");
                        System.out.println("현재 랭크 : 골드 " +  score);
                    } else if (score == 1150) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("실버로 승급하셨습니다.  ");
                        System.out.println("현재 랭크 : 실버 " +  score);
                    } else if (score == 800) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("브론즈로 승급하셨습니다.  ");
                        System.out.println("현재 랭크 : 브론즈 " +  score);
                    } else {
                        System.out.println("현재 점수 : " +  score);
                    }
                    return;
                } else {
                    score -= 20;
                    String sql2 = "UPDATE USER_INFO SET POINT =" + score + " WHERE ID = '" + currentID + "'";
                    rs = stmt.executeQuery(sql2);
                    System.out.println("패배");
                    if (score == 2800) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'CHALLENGER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("마스터로 강등되었습니다. ");
                        System.out.println("현재 랭크 : 마스터 " +  score);
                    } else if (score == 2150) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("플래티넘으로 강등되었습니다.  ");
                        System.out.println("현재 랭크 : 플래티넘 " +  score);
                    } else if (score == 1850) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("골드로 강등되었습니다.  ");
                        System.out.println("현재 랭크 : 골드 " +  score);
                    } else if (score == 1500) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("실버로 강등되었습니다.  ");
                        System.out.println("현재 랭크 : 실버 " +  score);
                    } else if (score == 1150) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("브론즈로 강등되었습니다.  ");
                        System.out.println("현재 랭크 : 브론즈 " +  score);
                    } else if (score == 800) {
                        String sqlCH = "UPDATE USER_INFO SET RANK = 'MASTER' WHERE ID = '" + currentID + "'";
                        int change = stmt.executeUpdate(sqlCH);
                        if (change > 0) System.out.println("아이언으로 강등되었습니다.  ");
                        System.out.println("현재 랭크 : 아이언 " +  score);
                    } else {
                        System.out.println("현재 점수 : " +  score);
                    }
                }
                return;
            }

            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
