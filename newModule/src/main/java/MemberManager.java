import dao.base.StringKeyEntityDao;
import data.MemberDao;
import entities.Member;

import java.util.Scanner;

public class MemberManager {

    private String id;
    private String password;
    private boolean isLogin;

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

    public boolean login(){
        loginMenu();

        Member query = MemberDao.getInstance().getByKey(id);
        if(query == null)
            return false;
        if(query.getPassword().equals(password))
            return true;
        else
            System.out.println("password is wrong!");
        return false;
    }


    public boolean logout(){
        //TODO:
        setLogin(false);
        return false;
    }

    public boolean join(){
        //TODO: 기능구현 Member 테이블에 insert
        //사용 안 하는 메서드
        //초기 보유잔액(balance) INITIAL_BALANCE 사용
        MemberDao.getInstance().
            }

    private void loginMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("아이디를 입력하세요.:");
        id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요.:");
        password = sc.nextLine();
    }

}
