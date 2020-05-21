import dao.base.StringKeyEntityDao;

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

     }


    public void logout() {
    }

    public void join() {
    }
}
