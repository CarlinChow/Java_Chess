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
   public void testUserInterface() {
      UserInterface userInterface = new UserInterface();
      userInterface.start();
   }
}
