package jdbc;

import jdbc.dao.LoLChamDAO;
import jdbc.dao.LoLSkinDAO;
import jdbc.dao.MemberDAO;
import jdbc.vo.LoLChamVO;
import jdbc.vo.LoLSkinVO;
import jdbc.vo.MemberVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    private static String loginSuccess = "";

    public static String getLoginSuccess() {
        return loginSuccess;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean Success = false;
        while (true) {
            System.out.println("====== [EMP Table Command] =======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.print("[1]로그인, [2]회원가입 [3]종료 ");
            int sel = sc.nextInt();
            MemberDAO memberdao = new MemberDAO();
            switch (sel) {
                case 1:
                    while (true) {
                        JdbcMain main = new JdbcMain();
                        loginSuccess = main.login();
                        if (!loginSuccess.equals("실패")) {
                            Success = true;
                            System.out.println(loginSuccess + "님 환영합니다.");
                            break;
                        } else {
                            System.out.println("잘못된 ID입니다.");
                        }
                    }
                    break;
                case 2:
                    memberdao.userInfoInsert();
                    JdbcMain main = new JdbcMain();
                    System.out.println(loginSuccess + "님 환영합니다.");
                    Success = true;
                    break;
                case 3:
                    System.out.println("종료");
                    Success = false;
                    break;
            }
            if (Success) {
                while (true) {
                    System.out.println("=============");
                    System.out.println("메뉴를 선택하세요 : ");
                    System.out.print("[1]게임시작, [2]상점, [3]로그아웃 ");
                    int sel2 = sc.nextInt();
                    switch (sel2) {
                        case 1:
                            System.out.println("게임을 시작합니다.");

                            break;
                        case 2:
                            System.out.println("어떤 상점에 입장하시겠습니까 : ");
                            System.out.print("[1]챔피언 상점, [2]스킨 상점 ");
                            int shopSel = sc.nextInt();
                            if (shopSel == 1) {
                                LoLChamDAO lolchamdao = new LoLChamDAO();
                                List<LoLChamVO> list = lolchamdao.LoLChamSelect();
                                lolchamdao.LoLSelectPrint(list);
                                lolchamdao.LoLChamInsert();
                            }
                            if (shopSel == 2) {
                                System.out.print("챔피언 이름을 입력하세요 : ");
                                LoLSkinDAO lolskindao = new LoLSkinDAO();
                                List<LoLSkinVO> list2 = lolskindao.LoLSkinBuySelect();
                                lolskindao.LoLSKinBuySelectPrint(list2);
                            }
                            break;
                        case 3:
                            System.out.println("로그아웃합니다");
                            break;
                    }
                    if(sel2 == 3) {
                        break;
                    }
                }
            }if(sel == 3){
                break;
            }
        }
    }
    public String login() {
        boolean Success = false;
        MemberDAO memberdao = new MemberDAO();
        List<MemberVO> list = memberdao.memberSelect();
        String loginSuccess = memberdao.lolLogin(list);
        if (!loginSuccess.equals("실패")) {
            Success = true;
            return loginSuccess;
        }
        return loginSuccess;
    }

}