package package1;
import java.util.Scanner;
/**
 * EditorCLI class provides a text-based user interface to the editor
 * supporting a read-process-print loop.
 *
 * @author YOUR NAME(S)
 *
 */
public class EditorCLI {

        public static void main(String[] args) {
                // COMPLETE THIS METHOD TO IMPLEMENT A
                // COMMAND-LINE USER INTERFACE (CLI) TO THE EDITOR
                Editor ed = new Editor();
                String x;
                Scanner scan = new Scanner(System.in);

                while(true){
                        try {

                                System.out.print("ted> ");
                                x = scan.nextLine();
                                ed.processCommand(x);

                        }
                        catch (EditorException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }

                }
        }

}