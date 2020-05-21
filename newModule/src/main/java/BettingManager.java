import data.MatchDao;
import data.TeamDao;
import entities.Betting;

import java.util.Scanner;

public class BettingManager {
    public void betting() {
        Betting bettingBean = new Betting(); //배팅 정보를 임시로 넣을 객체

        MatchDao.getInstance().showMatch();

        bettingBean.setMatchTime(MatchDao.getInstance().selectMatch());
        bettingBean.setBettingTeam(TeamDao.getInstance().selectTeam());

        int inputMoney=0;
        while((inputMoney = inputBettingMoney())<=0){}  //TODO:베팅 취소시 탈출조건 어디에 넣어야?

        bettingBean.setBettingMoney(inputMoney);
        bettingBean.setId(MemberManager.getInstance().getCurrentMember().getId());
        //TODO: Betting 테이블에 bettingBean을 insert
    }
    public int inputBettingMoney(){
        //입력금액 오류시 -1 리턴 ,그 외 베팅금액 리턴
        final int MIN_BETTING = 1000; //베팅 가능 최소금액
        final int MAX_BETTING = 50000; //베팅 가능 최대금액

        System.out.println("배팅할 금액을 입력하세요.:");
        Scanner sc = new Scanner(System.in);
        int inputValue;
        inputValue = sc.nextInt();
        sc.nextLine();

        if (inputValue >= MIN_BETTING){
            if(MemberManager.getInstance().getCurrentMember().getBalance()< inputValue){
                System.out.println("잔액이 부족합니다.");
                return -1;
            }
            else if(inputValue > MAX_BETTING){
                String str = String.format("최대 베팅 가능한 금액은 %d 입니다.",MAX_BETTING);
                System.out.println(str);
                return -1;
            }
            else{ //잔액 있을때
                return inputValue;
            }
        }
        //최소금액 미달
        String str = String.format("최소 베팅 가능한 금액은 %d 입니다.",MIN_BETTING);
        System.out.println(str);

        return -1;
    }


}
