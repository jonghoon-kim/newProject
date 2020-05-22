package util;

public class ExitStatus {
    public final int FAILED = -1;
    public final int ESCAPE = -2;

    private int exitStatus = 0;

    public void setEscapeStatus(){
        exitStatus = ESCAPE;
    }

    public void setFailedStatus(){
        exitStatus = FAILED;
    }

    public void setDefaultStatus(){
        exitStatus = 0;
    }

    public boolean isEscape(){
        return (exitStatus == ESCAPE);
    }

    public boolean isFail(){
        return (exitStatus == FAILED);
    }

    public void validateInputRange(){

    }

}
