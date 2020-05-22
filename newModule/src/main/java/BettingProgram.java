import entities.Member;
import util.ExitStatus;

import java.util.Scanner;


public class BettingProgram {

    private boolean exit = false;

    public void showMenu(){
        Member currentMember = MemberManager.getInstance().getCurrentMember();

        String menuString=null;
        if(currentMember!=null) {
            String statusString = String.format("id : %s | balance : %d",currentMember.getId(),currentMember.getBalance());
            System.out.println(statusString);
            menuString = "MENU | Logout(O) Betting(B) Exit(X)";
        } else
            menuString = "MENU | Login(L) Join(J) Exit(X)";
        System.out.println(menuString);
        System.out.print("->");
    }

    private String inputCommand() {
        Scanner sc = new Scanner(System.in);
        String inputString;
        String command;

        inputString = sc.nextLine();
        command = inputString.toLowerCase();
        return command;
    }

    public static void main(String[] args) {

        ExitStatus exit = new ExitStatus();

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
                    exit.setEscapeStatus();
                    break;
                default:
                    System.out.println("잘못된 명령을 입력하였습니다.");
                    break;

            }
            if(exit.isEscape())
                break;
        }

    }



}
