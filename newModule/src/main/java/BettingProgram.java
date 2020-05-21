import data.BettingDao;
import data.MatchDao;
import data.MemberDao;
import entities.Betting;
import entities.Match;
import entities.Member;
import entities.Team;

import java.util.Scanner;


/*
경기일정 보기(현재 날짜 이후 베팅가능한 경기만)

자신이 걸고 싶은 경기일정-팀을 선택-돈을 걸기
경기결과 보기(결과 나온 경기)
팀 테이블 내용 보기

 */
public class BettingProgram {
    private boolean isLogin = false;

    private boolean exit = false;

    //private Login login = Login.getInstance();

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void showMatch(){
        //TODO:
        //경기일정 보기(현재 날짜 이후 베팅가능한 경기만)
        //select(match 테이블)
        // 조건 - 결과(이긴팀)값이 null)
    }

    public Match selectMatch(){
        System.out.println("배팅할 게임 번호를 입력하세요.");

        Scanner sc = new Scanner(System.in);

        String matchKey = sc.nextLine();
        //bettingNo(pk)로 베팅할 게임 선택
        return MatchDao.getInstance().getByKey(matchKey);
    }

    public Team selectTeam(Match match){
        String command=null;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("팀을 선택하세요. 1.HomeTeam 2.AwayTeam");
            System.out.println("->");

            command = sc.nextLine();

            switch (command){
                case "1":
                    break;
                case "2":
                    break;
                default:
                    System.out.println("입력이 잘못되었습니다.다시 입력하세요.");
            }
        }

        //match.getAwayTeam();
        //match.getHomeTeam()
        //System.out.println();
    }

    public void scanInt(){

    }
    public void scanString(){

    }
    public boolean betting(){
        //TODO:기능 구현
        //베팅할 매치 불러오기

        Betting newBetting=new Betting();

        Match selectedMatch=null;
        Team selectedTeam=null;

       String matchTime=null;
        int bettingMoney;
        String battingTeam;
        //allocationMoney???
        String id = login.getId();

        //select match
        while(selectedMatch==null){
            selectedMatch = selectMatch();

        }  //selectMatch가 정상값이 나올떄까지 반복
        matchTime = selectedMatch.getMatchTime();



        while(selectedTeam==null){
            selectedTeam = selectTeam();
        }
        while(true){
            MatchDao.getInstance().getByKey()
            BettingDao.getInstance().insert();
            //베팅할 금액 입력

            //베팅 테이블에 insert
        }

        return false;
    }

    public void showMenu(){
        String menuString=null;
        if(login.isLogin()) {
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
        MemberManager.getInstance().join();

        String inputString;
        String command;

        while(true)  {
            betting.showMenu(); //L:login J:join B:betting(login) X:exit
            /*
            Scanner sc = new Scanner(System.in);


            inputString = sc.nextLine();
            command = inputString.toLowerCase();
            */
            ;

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
        return null;
    }
}
