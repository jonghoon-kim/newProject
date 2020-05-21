import lombok.Data;

@Data
public class Login {
    private String id;
    private String password;
    private boolean isLogin;

    //region singleton
    private Login() {
    }

    private static Login _instance;

    public static Login getInstance(){
        if (_instance == null)
            _instance = new Login();

        return _instance;
    }

    //endregion

}
