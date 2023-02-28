import dao.LoLChamDAO;
import dao.LoLSkinDAO;
import dao.LoLUserDAO;
import dao.MemberDAO;
import vo.LoLChamVO;
import vo.LoLSkinVO;
import vo.MemberVO;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class JdbcMain {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        MemberDAO memDao = new MemberDAO();
        LoLUserDAO userDao = new LoLUserDAO();
        LoLChamDAO chamDao = new LoLChamDAO();
        LoLSkinDAO skinDao = new LoLSkinDAO();


        int loginState = 0;
        boolean Success = false;
        while (true) {
            System.out.println("====== [EMP Table Command] =======");
            System.out.println("메뉴를 선택하세요 : ");
            System.out.print("[1]로그인, [2]회원가입, [3]종료");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    List<MemberVO> list = memDao.memberSelect();
                    MemberDAO memberdao = new MemberDAO();
                    String loginSuccess = memberdao.lolLogin(list);
                    System.out.println(loginSuccess);
                    if (loginSuccess.equals("성공")) {
                        Success = true;
                    }
                    break;
                case 2:
                    userDao.userInfoInsert1();
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
                        while (true) {
                            System.out.println("============[상점]============");
                            System.out.println("메뉴를 선택 하세요 : ");
                            System.out.print("[1]챔피언상점, [2] 스킨상점, [3]EXIT");
                            int sel2 = sc.nextInt();
                            switch (sel2) {
                                case 1:
                                    List<LoLChamVO> list = chamDao.LoLChamSelect();
                                    chamDao.LoLSelectPrint(list);
                                    chamDao.LoLChamInsert();
                                    continue;
                                case 2:
                                    List<LoLSkinVO> list2 = skinDao.LoLSkinSelect();
                                    skinDao.LoLSelectPrint(list2);
                                    System.out.println("구매하실 챔피언 이름을 입력해 주세요 : ");
                                    List<LoLSkinVO> list3 = skinDao.LoLSkinBuySelect();
                                    skinDao.LoLSKinBuySelectPrint(list3);
                                    skinDao.LoLSkinInsert();
                                    continue;
                                case 3:
                                    System.out.println("메뉴를 종료 합니다.");
                                    return;
                            }
                        }
//                    case 3:
//                        System.out.println("메뉴를 종료 합니다.");
//                        break;
                }
            }
        }
    }
}


////import vo.MemberVO;
////import dao.LolUserDAO;
////import java.util.List;
////import java.util.Scanner;
////
////        public class JdbcMain {
////            public static void main(String[] args) {
//                Scanner sc = new Scanner(System.in);
//                LolUserDAO dao = new LolUserDAO();
//                while (true) {
//                    System.out.println("====== [LOL Table Command] ======");
//                    System.out.println("메뉴를 선택하세요 : ");
//                    System.out.println("[1]회원조회 [2]회원가입 [3]EXIT");
//                    int sel = sc.nextInt();
//                    switch (sel) {
//                        case 1:
//                            List<MemberVO> list = dao.userInfoSelect();
//                            dao.userSelectPrint(list);
//                            break;
//                        case 2:
//                            dao.userInfoInsert();
//                            break;
//                        case 3:
//                            System.out.println("메뉴를 종료합니다.");
//                            return;
//                    }
//                }
//            }
//        }

