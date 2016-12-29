package package1;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * EditorTest class contains unit tests for Editor class.
 *
 * @author YOUR NAME(S)
 *
 * ADD MORE UNIT TESTS TO THIS CLASS
 *
 */
public class EditorTest {

        @Test
        public void testInsertCommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is an OO language.");
                ed.processCommand("i Ruby is another OO language.");
                assertEquals("Ruby is another OO " +
                                "language.", ed.getCurrentLine());
                assertEquals("Java is an OO language.", ed.getLine(0));
        }

        @Test
        public void testBCommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is an OO language.");
                ed.processCommand("i Ruby is another OO language.");
                ed.processCommand("b Hello World");
                assertEquals("Hello World", ed.getLine(1));
                assertEquals("Hello World", ed.getCurrentLine());

        }

        @Test
        public void testECommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Line1.");
                ed.processCommand("i Line2.");
                ed.processCommand("i Line3.");
                ed.processCommand("i Line4.");
                ed.processCommand("i Line5.");
                ed.processCommand("e Line6.");
                assertEquals("Line6.", ed.getLine(5));
                assertEquals("Line6.", ed.getCurrentLine());
                ed.processCommand("e Line7.");
                assertEquals("Line7.", ed.getLine(6));
                assertEquals("Line7.", ed.getCurrentLine());
        }

        @
        Test
        public void testUNCommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Line1.");
                ed.processCommand("i Line2.");
                ed.processCommand("i Line3.");
                ed.processCommand("i Line4.");
                ed.processCommand("i Line5.");
                ed.processCommand("i Line6.");
                ed.processCommand("i Line7.");
                ed.processCommand("i Line8.");
                ed.processCommand("i Line9.");
                ed.processCommand("i Line10.");
                ed.processCommand("u 5");
                assertEquals("Line5.", ed.getCurrentLine());
        }

        @
        Test
        public void testUMCommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("b Line1.");
                ed.processCommand("b Line2.");
                ed.processCommand("b Line3.");
                ed.processCommand("b Line4.");
                ed.processCommand("b Line5.");
                ed.processCommand("b Line6.");
                ed.processCommand("b Line7.");
                ed.processCommand("b Line8.");
                ed.processCommand("b Line9.");
                ed.processCommand("b Line10.");
                ed.processCommand("m 5");
                assertEquals("Line5.", ed.getCurrentLine());
        }


        @Test(expected=EditorException.class)
        public void testUNCommandError() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Line1.");
                ed.processCommand("i Line2.");
                ed.processCommand("i Line3.");
                ed.processCommand("i Line4.");
                ed.processCommand("i Line5.");
                ed.processCommand("i Line6.");
                ed.processCommand("i Line7.");
                ed.processCommand("i Line8.");
                ed.processCommand("i Line9.");
                ed.processCommand("i Line10.");
                ed.processCommand("u 12");
        }

        @Test(expected=EditorException.class)
        public void testUMCommandError() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("b Line1.");
                ed.processCommand("b Line2.");
                ed.processCommand("b Line3.");
                ed.processCommand("m 3");
        }


        @Test
        public void testbInsertCommand() throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("b Java is an OO language.");
                ed.processCommand("b All Java applications have a " +
                                "main method.");
                ed.processCommand("b Ruby is another OO language.");
                assertEquals("Ruby is another OO language.", ed.getLine(0));
                assertEquals("Ruby is another OO " +
                                "language.", ed.getCurrentLine());
        }

        @Test
        public void testUCommand() throws EditorException {
                Editor ed  = new Editor();
                ed.processCommand("i Line1");
                ed.processCommand("i Line2");
                ed.processCommand("i Line3");
                ed.processCommand("i Line4");
                ed.processCommand("u");
                ed.processCommand("u");
                assertEquals("Line2", ed.getCurrentLine());

        }

        @Test
        public void testMCommand() throws EditorException {
                Editor ed  = new Editor();
                ed.processCommand("i Line1");
                ed.processCommand("i Line2");
                ed.processCommand("i Line3");
                ed.processCommand("i Line4");
                ed.processCommand("u");
                ed.processCommand("u");
                assertEquals("Line2", ed.getCurrentLine());
                ed.processCommand("m");
                ed.processCommand("m");
                assertEquals("Line4", ed.getCurrentLine());

        }

        @Test
        public void testRCommand() throws EditorException {
                Editor ed  = new Editor();
                ed.processCommand("i Line1");
                ed.processCommand("i Line2");
                ed.processCommand("i Line3");
                ed.processCommand("i Line4");
                ed.processCommand("u");
                ed.processCommand("u");
                ed.processCommand("r");
                assertEquals("Line3", ed.getCurrentLine());
                assertEquals(3, ed.nbrLines());
        }

        @Test
        public void testClearCommand()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is cool.");
                ed.processCommand("i Python is cooler.");
                ed.processCommand("i Ruby is hot.");
                ed.processCommand("i COBOL is old.");
                ed.processCommand("c");
                assertEquals(0, ed.nbrLines());

        }

        @Test
        public void testRemoveCommand()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is cool.");
                ed.processCommand("i Python is cooler.");
                ed.processCommand("i Ruby is hot.");
                ed.processCommand("i COBOL is old.");
                assertEquals(4, ed.nbrLines());
                ed.processCommand("u 2");
                ed.processCommand("r 2");
                assertEquals(2, ed.nbrLines());
                assertEquals("COBOL is old.", ed.getCurrentLine());
        }

        @Test(expected=EditorException.class)
        public void testRemoveError()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is cool.");
                ed.processCommand("i Python is cooler.");
                ed.processCommand("i Ruby is hot.");
                ed.processCommand("i COBOL is old.");
                assertEquals(4, ed.nbrLines());
                ed.processCommand("u 2");
                ed.processCommand("r 2");
                assertEquals(2, ed.nbrLines());
                assertEquals("COBOL is old.", ed.getCurrentLine());
                ed.processCommand("r 2");//should cause an error
        }

        @Test
        public void testReverseCommand()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is cool.");
                ed.processCommand("i Python is cooler.");
                ed.processCommand("i Ruby is hot.");
                ed.processCommand("i COBOL is old.");
                assertEquals(4, ed.nbrLines());
                ed.processCommand("rev");
                assertEquals(4, ed.nbrLines());
                assertEquals("COBOL is old.", ed.getLine(0));
                assertEquals("Ruby is hot.", ed.getLine(1));
                assertEquals("Python is cooler.", ed.getLine(2));
                assertEquals("Java is cool.", ed.getLine(3));
        }


        @Test
        public void testRevCommand()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Line1");
                ed.processCommand("i Line2");
                ed.processCommand("i Line3");
                ed.processCommand("i Line4");
                ed.processCommand("i Line5");
                ed.processCommand("i Line6");
                ed.processCommand("i Line7");
                assertEquals(7, ed.nbrLines());
                assertEquals(6, ed.getCurrentLineNbr());
                ed.processCommand("rev");
                assertEquals(7, ed.nbrLines());
                assertEquals("Line7", ed.getLine(0));
                assertEquals("Line6", ed.getLine(1));
                assertEquals(6, ed.getCurrentLineNbr());

        }

        @Test(expected=EditorException.class)
        public void testRemoveException()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i Java is cool.");
                ed.processCommand("i Python is cooler.");
                ed.processCommand("i Ruby is hot.");
                assertEquals(3, ed.nbrLines());
                ed.processCommand("u 1");
                ed.processCommand("r 3"); // this call should throw EditorException
        }

        @Test
        public void testCut()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i line1.");
                ed.processCommand("i line2.");
                ed.processCommand("i line3.");
                ed.processCommand("i line4.");
                ed.processCommand("i line5.");
                ed.processCommand("i line6.");
                ed.processCommand("i line7.");
                ed.processCommand("i line8.");
                ed.processCommand("cut 02 08");
                assertEquals(1, ed.nbrLines());
                assertEquals("line1.", ed.getCurrentLine());
        }

        @Test
        public void testPaste()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i line1.");
                ed.processCommand("i line2.");
                ed.processCommand("i line3.");
                ed.processCommand("i line4.");
                ed.processCommand("i line5.");
                ed.processCommand("i line6.");
                ed.processCommand("i line7.");
                ed.processCommand("i line8.");
                ed.processCommand("cut 02 08");
                ed.processCommand("pas");
                assertEquals("line2.", ed.getCurrentLine());
        }


        @Test(expected=EditorException.class)
        public void testCutException()throws EditorException {
                Editor ed = new Editor();
                ed.processCommand("i line1.");
                ed.processCommand("i line2.");
                ed.processCommand("i line3.");
                ed.processCommand("i line4.");
                ed.processCommand("i line5.");
                ed.processCommand("i line6.");
                ed.processCommand("i line7.");
                ed.processCommand("i line8.");
                ed.processCommand("cut 02 09");

        }

}