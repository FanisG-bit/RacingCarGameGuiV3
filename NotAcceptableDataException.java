package Game;

import javax.swing.*;

public class NotAcceptableDataException extends Exception{
    public NotAcceptableDataException(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}