package chess.ui;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;

public class UserInterfaceTest {
    @Test
    public void TestCommandLineInput(){
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream inputStream = new ByteArrayInputStream("1\ne2 e4\n\n".getBytes());
        Scanner scanner = new Scanner(inputStream);
        UserInterface userInterface = new UserInterface(scanner);
        userInterface.start();
    }
}