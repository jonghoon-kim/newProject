import data.BettingDao;
import data.MatchDao;
import data.TeamDao;
import entities.Betting;

import java.util.Scanner;

public class BettingManager {
    private final int FAILED = -1;
    private final int CANCELED = -2;

    private final int MIN_BETTING = 1000; //베팅 가능 최소금액
    private final int MAX_BETTING = 50000; //베팅 가능 최대금액
    
    public void betting() {
        Betting bettingBean = new Betting(); //배팅 정보를 임시로 넣을 객체
        boolean exit = false; // betting() 탈출 조건 변수

        
        //MatchDao.getInstance().showMatch();

        //bettingBean.setMatchTime(MatchDao.getInstance().selectMatch());
        //bettingBean.setBettingTeam(TeamDao.getInstance().selectTeam());

        int inputMoney=0;
        
        exit = false;
        // 베팅금액 입력받기
        while(true){
            inputMoney = inputBettingMoney();
            if(inputMoney== CANCELED ){
                exit = true;
                break;
            }
            else if(inputMoney>=0){
                bettingBean.setBettingMoney(inputMoney);
                break;
            }

        }

        //bettingBean.setId(MemberManager.getInstance().getCurrentMember().getId());

        //BettingDao.getInstance().insert(bettingBean);
    }
    public int inputBettingMoney(){
        //탈출시 -2 입력
        // 금액 오류시 FAILED 리턴 ,그 외 베팅금액 리턴

        System.out.println("배팅할 금액을 입력하세요(-2 입력시 베팅 취소).:");
        Scanner sc = new Scanner(System.in);
        int inputValue;
        inputValue = sc.nextInt();
        sc.nextLine();
        
        if(inputValue == CANCELED)
            return CANCELED; //베팅 취소하고 탈출
        
        if (inputValue >= MIN_BETTING){
            if(MemberManager.getInstance().getCurrentMember().getBalance()< inputValue){
                System.out.println("잔액이 부족합니다.");
                return FAILED;
            }
            else if(inputValue > MAX_BETTING){
                String str = String.format("최대 베팅 가능한 금액은 %d 입니다.",MAX_BETTING);
                System.out.println(str);
                return FAILED;
            }
            else{ //잔액 있을때
                return inputValue;
            }
        }
        //최소금액 미달
        String str = String.format("최소 베팅 가능한 금액은 %d 입니다.",MIN_BETTING);
        System.out.println(str);

        return FAILED;
    }


}
