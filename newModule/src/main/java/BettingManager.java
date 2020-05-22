import data.BettingDao;
import data.MatchDao;
import data.MemberDao;
import data.TeamDao;
import entities.Betting;
import entities.Match;
import entities.Member;
import entities.Team;
import util.ExitStatus;

import java.util.ArrayList;
import java.util.Scanner;

public class BettingManager {

    private final int MIN_BETTING = 1000; //베팅 가능 최소금액
    private final int MAX_BETTING = 50000; //베팅 가능 최대금액
    
    public void betting() {

        Betting bettingBean = new Betting(); //배팅 정보를 임시로 넣을 객체
        ExitStatus exit = new ExitStatus();

        //경기 리스트 보여주기
        ArrayList<Match> matches = null;
        matches = MatchDao.getInstance().showMatch();
        if(matches == null){
            System.out.println("베팅할 경기가 없습니다.");
            return;
        }

        // 경기 선택
        Match match = null;
        exit.setDefaultStatus();
        while(match == null){
            match = MatchDao.getInstance().selectMatch(exit, matches);
            if(exit.isEscape())
                break;
        }
        if(exit.isEscape())
            return;

        bettingBean.setMatchTime(match.getMatchTime());

        // 팀 선택
        Team team = null;
        while(team==null){
            team = TeamDao.getInstance().selectTeam(exit,match);
            if(exit.isEscape())
                return;
        }

        bettingBean.setBettingTeam(team.getTeam());

        // 베팅금액 입력
        int inputMoney=0;
        exit.setDefaultStatus();
        while(true){
            inputMoney = inputBettingMoney();
            if(inputMoney== exit.ESCAPE){
                exit.setEscapeStatus();
                break;
            }
            else if(inputMoney>=0){
                bettingBean.setBettingMoney(inputMoney);
                break;
            }
        }
        if(exit.isEscape())
            return;

        bettingBean.setId(MemberManager.getInstance().getCurrentMember().getId());

        //베팅(테이블에 입력)
        if(BettingDao.getInstance().insert(bettingBean)){
            Member memberBean = MemberManager.getInstance().getCurrentMember();
            memberBean.setBalance(memberBean.getBalance()-inputMoney);

            MemberDao.getInstance().update(memberBean);
            System.out.println("베팅이 완료되었습니다.");
        }else
            {
            System.out.println("베팅이 실패하였습니다.");

        }

    }
    public int inputBettingMoney(){
        //탈출시 -2 입력
        // 금액 오류시 FAILED 리턴 ,그 외 베팅금액 리턴

        ExitStatus exit = new ExitStatus();

        System.out.println("배팅할 금액을 입력하세요(-2 입력시 베팅 취소).:");
        Scanner sc = new Scanner(System.in);
        int inputValue;
        inputValue = sc.nextInt();
        sc.nextLine();
        
        if(inputValue == exit.ESCAPE)
            return exit.ESCAPE; //베팅 취소하고 탈출
        
        if (inputValue >= MIN_BETTING){
            if(MemberManager.getInstance().getCurrentMember().getBalance()< inputValue){
                System.out.println("잔액이 부족합니다.");
                return exit.FAILED;
            }
            else if(inputValue > MAX_BETTING){
                String str = String.format("최대 베팅 가능한 금액은 %d 입니다.",MAX_BETTING);
                System.out.println(str);
                return exit.FAILED;
            }
            else{ //잔액 있을때
                return inputValue;
            }
        }
        //최소금액 미달
        String str = String.format("최소 베팅 가능한 금액은 %d 입니다.",MIN_BETTING);
        System.out.println(str);

        return exit.FAILED;
    }


}
