package package1;
import java.util.*;
import java.io.*;
import java.util.*;

/**
 * Editor class implements a simple line-oriented editor.
 *
 * @author YOUR NAME(S)
 *
 */
public class Editor implements IEditor {

        private MyLinkedList list = new MyLinkedList();
        private String command;
        private ArrayList al = new ArrayList();


        // add other fields as needed

        @Override
        public void processCommand(String command) throws EditorException {
                Scanner scan = new Scanner(System.in);

                if(command.equals("rev")){
                        this.rev();
                        return;
                }

                if(command.equals("pas")){
                        this.paste();
                        return;
                }


                char operation = command.charAt(0);
                int x;

                switch (operation)
                {
                case 'e':
                        //adds the line to back of list
                        String element = command.substring(2);
                        list.add(element);
                        break;
                case 'b':
                        //finds the index before current
                        x = this.getCurrentLineNbr();
                        if(x < 0)
                                x = 0;
                        list.add(x, command.substring(2));
                        break;

                case 'i':
                        //finds the index after current
                        x = this.getCurrentLineNbr() + 1;
                        if(list.isEmpty()){
                                list.add(command.substring(2));
                                break;
                        }
                        list.add(x, command.substring(2));
                        break;

                case 'm':
                        //moves the current line down
                        if(command.length() == 1){
                                if(!list.isEmpty()){
                                        x = list.getCurrentNbr() + 1;
                                        list.setCurrent(x);
                                        break;
                                }
                                else{
                                        throw new EditorException("list is empty.");
                                }
                        }

                        else if((this.getCurrentLineNbr() +
                                        Integer.parseInt(command.substring(2))) >=
                                        (list.size())
                                        || list.isEmpty()){
                                throw new EditorException("Move not possible.");
                        }


                        else{
                                x = list.getCurrentNbr() +
                                                Integer.parseInt(command.substring(2));
                                list.setCurrent(x);
                                break;
                        }

                case 'u':
                        //moves the current line down
                        if(command.length() == 1) {
                                if(!list.isEmpty()){
                                        x = list.getCurrentNbr() - 1;
                                        list.setCurrent(x);
                                        break;
                                }
                                else{

                                        throw new EditorException("list is empty.");
                                }
                        }


                        else if((this.getCurrentLineNbr() -
                                        Integer.parseInt(command.substring(2)) < 0))

                                throw new EditorException("Move not possible.");

                        else{
                                x = list.getCurrentNbr() -
                                                Integer.parseInt(command.substring(2));
                                list.setCurrent(x);
                                break;
                        }

                case 'r':

                        if(this.nbrLines() == 0)
                                //throws exception if the list is empty
                                throw new EditorException("The list is empty.");
                        else{

                                //list.remove(list.getCurrentNbr());
                                this.removeLines(command);
                                break;
                        }

                case 'd':
                        //displays the list in a nice format
                        this.display(command);
                        break;

                case 'c':
                        //clears the buffer of all items
                        if(command.length() > 1){

                                String y = command.substring(command.length()-2);
                                int a = Integer.parseInt(command.substring(4, 6)) - 1;
                                int b = Integer.parseInt(y);
                                this.cut(a, b);
                                break;
                        }

                        System.out.print("You are about to clear the editor " +
                                        "are you sure you want to procceed: ");
                        String choice = scan.nextLine();

                        if(choice.equals("y")){
                                this.clear();
                                break;
                        }

                        else if(choice.equals("n"))
                                break;

                        else{
                                System.out.println("Invalid Choice.");
                                break;
                        }

                case 's':
                        try{
                                this.save(command.substring(2));
                                break;
                        }
                        catch(StringIndexOutOfBoundsException e){
                                System.out.println("No File.");
                                break;
                        }


                case 'l':
                        //clears buffer then loads new items from file
                        if(command.length() == 1){
                                System.out.println("No File.");
                                break;
                        }

                        else{
                                System.out.print("Are you sure you want to load " +
                                                "this file: ");
                                choice = scan.nextLine();
                                this.load(choice, command.substring(2));
                                break;
                        }

                case 'h':
                        //displays a description of commands
                        this.help();
                        break;

                case 'x':
                        //exits the program
                        System.out.print("Are you sure you want to exit: ");
                        choice = scan.nextLine();
                        this.exit(choice);
                        break;
                default:
                        System.out.println("Error");
                }

        }

        /**
         * Gets the number lines.
         *
         * @return the number of lines.
         */
        @Override
        public int nbrLines() {

                return list.size();
        }

        /**
         * Gets the specified node.
         *
         * @param lineNbr the index number of the node.
         * @return the node by specified line index.
         */
        @Override
        public String getLine(int lineNbr) throws EditorException {
                if(lineNbr < 0 || lineNbr > list.size()){
                        throw new EditorException("Line number is invalid.");
                }

                return list.get(lineNbr);
        }

        /**
         * Gets the contents current node.
         *
         * @return the contents current node.
         */
        @Override
        public String getCurrentLine() {
                // REPLACE THE LINE BELOW WITH YOUR CODE
                return list.getCurrent().getData();
        }

        /**
         * Gets the index of the current node.
         *
         * @return index of current node.
         */
        @Override
        public int getCurrentLineNbr() {
                // REPLACE THE LINE BELOW WITH YOUR CODE
                return list.getCurrentNbr();
        }

        /**
         * Displays the buffer in a formatted form.
         *
         */
        public void display(String command){

                int i;
                int x;

                //prints the entire list
                if(command.length() == 1){
                        System.out.println("Start of file.");
                        i = 0;
                        //loops through and gets the info from the list
                        while(i <= (this.nbrLines()-1)){
                                if(list.getNode(i) == list.getCurrent())
                                        System.out.println("*" + (i+1) + ": " +
                                                        list.get(i).toString());
                                else
                                        System.out.println(" " + (i+1) + ": " +
                                                        list.get(i).toString());
                                i++;
                        }
                        System.out.println("End of file.");
                }

                else{

                        //Gets the number lines to print
                        i = Integer.parseInt(command.substring(2, 4));
                        x = Integer.parseInt(command.substring(5));

                        System.out.println("Start of file.");

                        //loops to get the info from the list
                        while((i-1) <= (x-1)){

                                System.out.println(" " + (i) + ": " +
                                                list.get(i-1).toString());
                                i++;
                        }

                        System.out.println("End of file.");

                }

        }


        /**
         * Clears the buffer
         *
         */
        public void clear(){
                list.clear();
        }


        /**
         * Saves the buffer to a file.
         *
         */
        public void save(String fileName){

                int i = 1;

                try{

                        File linkInfo = new File(fileName);
                        //creates the file to save to
                        PrintWriter infoToWrite = new PrintWriter(
                                        new BufferedWriter(new FileWriter(linkInfo)));
                        //prints the list to the file
                        while(i <= this.nbrLines()){

                                infoToWrite.println(list.get(i-1));
                                i++;
                        }

                        infoToWrite.close();
                }

                catch(IOException e){

                        System.out.println("An I/O Error Occured.");
                        System.exit(0);
                }

        }

        /**
         * Clears the buffer and the loads a file into it.
         *
         */
        public void load(String choice, String x){

                if(choice.equals("y")){
                        this.clear();

                        File linkedInfo = new File(x);

                        try{
                                //sets up the file to load from
                                BufferedReader getInfo = new BufferedReader(
                                                new FileReader(linkedInfo));

                                String info = getInfo.readLine();
                                list.add(info);

                                //reads and adds the lines to the list
                                while(info != null){
                                        info = getInfo.readLine();

                                        if(info == null)
                                                break;

                                        list.add(info);
                                }

                                list.setCurrent(0);
                                getInfo.close();

                        }

                        catch(FileNotFoundException e){
                                System.out.println("Couldn't Find File.");
                                System.exit(0);
                        }

                        catch(IOException e){
                                System.out.print("An I/O Exception has occured.");
                                System.exit(0);
                        }
                }

                else if(choice.equals("n"))
                        return;

                else{
                        System.out.println("Invalid Choice.");
                        return;
                }


        }

        /**
         * Exits the program
         *
         */
        public void exit(String choice){

                //Asks if you are sure you want to quit
                if(choice.equals("y")){
                        System.out.println("Bye!");
                        System.exit(0);
                }

                else if(choice.equals("n"))
                        return;

                else{
                        System.out.println("Invalid Choice.");
                        return;
                }


        }

        /**
         * Displays a help section that describes the functions
         * of the commands.
         *
         */
        public void help(){
                System.out.println("b <sentence>:  Insert sentence before " +
                                "the current; makes the inserted line the " +
                                "current line.");
                System.out.println();
                System.out.println("i <sentence>: Insert sentence after " +
                                "the current; makes the inserted line the " +
                                "current line.");
                System.out.println();
                System.out.println("m   Move current line " +
                                "indicator down 1 position.");
                System.out.println();
                System.out.println("u   Move current line " +
                                "indicator up 1 position.");
                System.out.println();
                System.out.println("r  Remove the current line; make " +
                                "the next line the current line, if there " +
                                "is a line; otherwise \n   make the previous " +
                                "line the current line, if there is a previous " +
                                "line. Throws EditorException if \n   remove is not" +
                                " possible.");
                System.out.println();
                System.out.println("c   Removes all lines in the buffer. ");
                System.out.println();
                System.out.println("s <filename>   Save the contents " +
                                "to the specified file.");
                System.out.println();
                System.out.println("l <filename> Load the contents of the " +
                                "specified file into editor buffer replacing " +
                                "the current contents;  \n   the first line becomes " +
                                "the current line.");
                System.out.println();
                System.out.println("x   Exit the editor");
                System.out.println();
                System.out.println("e <sentence>   Insert line after the" +
                                " last line; make the inserted line the current line.");
                System.out.println();
                System.out.println("m n  Move current line indicator " +
                                "down n positions.");
                System.out.println();
                System.out.println("u n  Move the current line indicator " +
                                "up n positions.");
                System.out.println();
                System.out.println("r n   Remove n lines at the " +
                                "current line; makes the next line the current " +
                                "line, \n      if there is a next line; otherwise " +
                                "make the previous line the current line.");
                System.out.println();
                System.out.println("d n1 n2  Display lines from line " +
                                "n1 to line n2.");
                System.out.println();
                System.out.println("rev   Reverses the lines in the buffer.");
                System.out.println();
                System.out.println("cut n1 n2  Cut the lines from the " +
                                "file from line1 n1 to line n2 (inclusive), and" +
                                " copy to \n    an internal clipboard.");
                System.out.println();
                System.out.println("pas  Paste the clipboard contents " +
                                "before the current line position.\n     Make the " +
                                "first line of the lines pasted the current line.");

        }

        /**
         * Removes lines from the list.
         *
         */

        public void removeLines(String command) throws EditorException {
                //checks if I'm removing 1 line
                if((command.length() == 1) ||
                                (Integer.parseInt(command.substring(2)) == 1))
                        list.remove(list.getCurrentNbr());

                else{

                        int x = Integer.parseInt(command.substring(2));
                        //checks to see if the remove goes out of bounds
                        if((list.getCurrentNbr() + x) > (list.size()))
                                throw new EditorException("Removal Invalid");


                        for(int i = 0; i < x; i++){

                                list.remove(list.getCurrentNbr());

                        }
                }


        }

        /**
         * Reverses the order of the lines
         *
         */
        public void rev(){
                Stack st = new Stack();
                int i = 0;
                int x = 0;

                //loops through list and pops the strings into st
                while(i <= (this.nbrLines()-1)){
                        st.push(list.get(i));
                        i++;
                }

                //clears list
                list.clear();

                //loops through st and adds the elements back to list
                while(!st.empty()){
                        list.add((String) st.pop());
                }

                return;
        }

        /**
         * Saves and removes the indicated elements
         *
         */
        public void cut(int a, int b) throws EditorException{
                if(list.size() < (b))
                        throw new EditorException("Invalid Cut");

                //I'll save position a to x because I'll need to know
                //where to remove elements from the list
                int x = a;

                while(a < b){

                        al.add(list.get(a));
                        a++;
                }

                list.remove(x);
                x++;
                while(x < b){

                        list.remove(list.getCurrentNbr());
                        x++;
                }

        }

        /**
         * Pastes the lines saved
         *
         */
        public void paste(){
                //pastes the lines saved in ascending order
                //makes the first pasted the new current
                for(int i = (al.size()-1); i >= 0; i--){
                        list.add(list.getCurrentNbr(), (String)al.get(i));
                }
        }

}