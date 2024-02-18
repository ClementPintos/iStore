package Utils;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean emailValide(String email){
        String emailReg = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailReg);
        if(email == null){
            return false;
        }
        else return pattern.matcher(email).matches();
    }

}
