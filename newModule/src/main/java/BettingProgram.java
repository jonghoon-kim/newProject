import data.BettingDao;
import data.MatchDao;
import entities.Betting;
import entities.Match;
import entities.Team;

import java.util.Scanner;


/*
경기일정 보기(현재 날짜 이후 베팅가능한 경기만)

자신이 걸고 싶은 경기일정-팀을 선택-돈을 걸기
경기결과 보기(결과 나온 경기)
팀 테이블 내용 보기

 */
public class BettingProgram {

    private boolean exit = false;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void showMenu(){
        String menuString=null;
        if(MemberManager.getInstance().getCurrentMember()!=null) {
            menuString = "MENU | Logout(O) Betting(B) Exit(X)";
        } else
            menuString = "MENU | Login(L) Join(J) Exit(X)";
        System.out.println(menuString);
        System.out.print("->");
    }

    public static void main(String[] args) {
        //tring matchTime = "";
        //MatchDao.getInstance().calculateBenefit(matchTime);
        BettingManager bettingManager = new BettingManager();
        BettingProgram betting = new BettingProgram();

        while(true)  {
            betting.showMenu(); //L:login J:join B:betting(login) X:exit

            switch (betting.inputCommand()){
                case "l":
                    MemberManager.getInstance().login();
                    break;
                case "o":
                   MemberManager.getInstance().logout();
                   break;
                case "j":
                    MemberManager.getInstance().join();
                    break;
                case "b":
                    bettingManager.betting();
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

    private String inputCommand() {
        Scanner sc = new Scanner(System.in);
        String inputString;
        String command;

        inputString = sc.nextLine();
        command = inputString.toLowerCase();
        return command;
    }

}
