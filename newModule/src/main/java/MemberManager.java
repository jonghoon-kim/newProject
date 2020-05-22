import data.MemberDao;
import entities.Member;

import java.util.Scanner;

public class MemberManager {
    private Member currentMember = null;  //현재 로그인한 멤버
    private Member memberBean = new Member();
    private final int INITIAL_BALANCE = 100000; // 가입 지급 금액

    //region singleton
    private MemberManager() {
    }

    private static MemberManager _instance;

    public static MemberManager getInstance(){
        if (_instance == null)
            _instance = new MemberManager();

        return _instance;
    }

    //endregion

    public Member getCurrentMember() {
        return currentMember;
    }

    public void setCurrentMember(Member currentMember) {
        this.currentMember = currentMember;
    }

    public void login(){
        inputId();
        inputPassword();

        Member selectedMember = MemberDao.getInstance().getByKey(memberBean.getId());

        if(selectedMember == null){
            System.out.println("존재하지 않는 멤버입니다.다시 입력하세요.");
            return;
        }

        if(selectedMember.getPassword().equals(memberBean.getPassword())){
            setCurrentMember(selectedMember);
            return;
        }
        else
            System.out.println("패스워드가 잘못되었습니다.");
            return;

    }

    public void logout(){
        if(getCurrentMember()==null)
            System.out.println("현재 로그인 상태가 아닙니다.");
        else{
            System.out.println("로그아웃 하였습니다.");
            setCurrentMember(null);
        }
    }

    public void join(){

        inputId();
        Member selectedMember = MemberDao.getInstance().getByKey(memberBean.getId());
        if(selectedMember!=null){
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }
        inputPassword();
        memberBean.setBalance(INITIAL_BALANCE);
        if(MemberDao.getInstance().insert(memberBean) == true){
            System.out.println("가입이 성공하였습니다.");
            return;
        }
        System.out.println("가입이 실패하였습니다.");
    }

    private void inputId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디를 입력하세요.:");
        memberBean.setId(sc.nextLine());

    }
    private void inputPassword(){
        Scanner sc = new Scanner(System.in);
        System.out.println("비밀번호를 입력하세요.:");
        memberBean.setPassword(sc.nextLine());
    }

}