import dao.LolUserDAO;
import dao.MemberDAO;
import vo.LolUserVO;
import vo.MemberVO;

import java.util.List;
import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MemberDAO dao = new MemberDAO();
        int loginState = 0;
        boolean Success = false;
        while (true) {
            System.out.println("====== [EMP Table Command] =======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.print("[1]로그인, [2]회원가입 [3]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<MemberVO> list = dao.memberSelect();
                    MemberDAO memberdao = new MemberDAO();
                    String loginSuccess = memberdao.lolLogin(list);
                    System.out.println(loginSuccess);
                    if (loginSuccess.equals("성공")) {
                        Success = true;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("종료");
                    break;
            }
            break;
        }
        if (Success) {
            while (true) {
                System.out.println("=============");
                System.out.println("메뉴를 선택하세요 : ");
                System.out.print("[1]게임시작, [2]상점, [3]종료");
                int sel = sc.nextInt();
                switch (sel) {
                    case 1:
                        System.out.println("게임을 시작합니다.");
                        break;
                    case 2:
                        System.out.println("상점에 입장합니다.");
                        break;
                    case 3:
                        System.out.println("종료합니다");
                        break;
                }
                break;
            }
        }


        MemberDAO dao = new MemberDAO();
        while (true) {
            System.out.println("====== [LOL Table Command] ======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.println("[1]회원조회 [2]회원가입 [3]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<MemberDAO> list = dao.userInfoSelect();
                    dao.userSelectPrint(list);
                    break;
                case 2:
                    dao.userInfoInsert();
                    break;
                case 3:
                    System.out.println("메뉴를 종료합니다.");
                    return;
            }
        }


        Scanner sc = new Scanner(System.in);
        LoLChamDAO dao = new LoLChamDAO();
        LoLSkinDAO dao2 = new LoLSkinDAO();
        while (true) {
            System.out.println("============[상점]============");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.print("[1]챔피언 상점, [2] 스킨 상점, [3]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<LoLChamVO> list = dao.LoLChamSelect();
                    dao.LoLSelectPrint(list);
                    dao.LoLChamInsert();
                case 2:
                    List<LoLSkinVO> list2 = dao2.LoLSkinSelect();
                    dao2.LoLSelectPrint(list2);
                    System.out.println("구매하실 챔피언 이름을 입력해 주세요 : ");
                    List<LoLSkinVO> list3 = dao2.LoLSkinBuySelect();
                    dao2.LoLSKinBuySelectPrint(list3);
                    dao2.LoLSkinInsert();
                case 3:
                    System.out.println("메뉴를 종료 합니다.");
                    return;

            }
        }
    }
}
