import data.BettingDao;
import data.MatchDao;

import java.util.Scanner;

/*
경기일정 보기(현재 날짜 이후 베팅가능한 경기만)

자신이 걸고 싶은 경기일정-팀을 선택-돈을 걸기
경기결과 보기(결과 나온 경기)
팀 테이블 내용 보기

 */
public class BettingModule {
    private boolean isLogin = false;
    private boolean loginId;
    private boolean exit = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLoginId() {
        return loginId;
    }

    public void setLoginId(boolean loginId) {
        this.loginId = loginId;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean login(){
        String id;
        String password;

        Scanner sc = new Scanner(System.in);

        System.out.println("아이디를 입력하세요.:");
        id = sc.nextLine();

        System.out.println("비밀번호를 입력하세요.:");
        password = sc.nextLine();

        //TODO:
        //return MemberDao.getInstance().insert(id,password,INITIAL_BALANCE);
        setLogin(true);
        return true;


    }

    public boolean logout(){
        //TODO:
        setLogin(false);
        return false;
    }

    public boolean join(){
        //TODO: 기능구현 Member 테이블에 insert
        //초기 보유잔액(balance) INITIAL_BALANCE 사용

        final int INITIAL_BALANCE = 100000;
        System.out.println("join");
        return false;
    }

    public void showMatch(){
        //TODO:
        //경기일정 보기(현재 날짜 이후 베팅가능한 경기만)
        //select(match 테이블)
        MatchDao.getInstance().getAll();
        MatchDao.getInstance().getByKey("");
        // 조건 - 결과(이긴팀)값이 null)

    }

    public boolean betting(){
        //TODO:기능 구현
        System.out.println("배팅할 게임 번호를 입력하세요.");
        //bettingNo(pk)로 베팅할 게임 선택

        //베팅할 금액 입력

        //베팅 테이블에 insert
        return false;
    }

    public void showMenu(){
        String menuString=null;
        if(isLogin()) {
            menuString = "MENU | Logout(O) Betting(B) Exit(X)";
        } else
            menuString = "MENU | Login(L) Join(J) Exit(X)";
        System.out.println(menuString);
        System.out.print("->");
    }

    public static void main(String[] args) {
        BettingModule betting = new BettingModule();

        String inputString;
        String command;

        while(true)  {
            betting.showMenu(); //L:login J:join B:betting(login) X:exit
            Scanner sc = new Scanner(System.in);


            inputString = sc.nextLine();
            command = inputString.toLowerCase();

            switch (command){
                case "l":
                    betting.login();
                    break;
                case "o":
                    betting.logout();
                case "j":
                    betting.join();
                    break;
                case "b":
                    betting.betting();
                    break;
                case "x":
                    betting.setExit(true);
                    break;
                default:
                    System.out.println("잘못된 명령을 입력하였습니다.");
                    break;

            }
            if(betting.isExit())
                break;
        }

    }
}
