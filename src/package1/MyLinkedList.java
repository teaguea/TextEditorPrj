package package1;
/**
 * The MyLinkedList class implements a simple linked list.
 *
 * @author YOUR NAME(S)
 *
 */
public class MyLinkedList implements ILinkedList {

        private Node front;
        private Node rear;
        private Node current;
        private int size;

        /**
         * Initializes a newly constructed MyLinkedList object.
         */
        public MyLinkedList() {
                front = null;
                rear = null;
                current = null;
                size = 0;
        }

        /**
         * Adds the specified element to the end of the list.
         *
         * @param element the element to add to the list
         *
         * @throws NullPointerException if the specified element is null
         */
        @Override
        public void add(String element)throws NullPointerException {
                // COMPLETE THIS METHOD
                if(element.equals(null)){
                        throw new NullPointerException();
                }



                //if the list is empty assign the new element to first
                if(front == null){
                        front = rear = new Node(element);
                        current = rear;
                        size++;

                }
                else{
                        rear.setNext(new Node(element));
                        rear = rear.getNext();
                        current = rear;
                        size++;
                }


        }

        /**
         * Inserts the given element at the specified position in the list.
         *
         * @param index index at which the specified element is to be
         * inserted.
         * @param element element to be inserted
         * @throws IndexOutOfBoundsException if the index is out of range
         * (index < 0 || index > size()).
         * @throws NullPointerException if the specified element is null
         */
        @Override
        public void add(int index, String element) throws
        IndexOutOfBoundsException, NullPointerException{

                if(index < 0 || index > size()){
                        throw new IndexOutOfBoundsException();
                }

                if(element.equals(null)){
                        throw new NullPointerException();
                }

                Node x = front;
                //creates a node with element
                Node temp = new Node(element);
                int i = 0;

                //what to do if no list
                if(isEmpty()){
                        front = rear = new Node(element);
                        front.setNext(null);
                        size++;
                        current = front;
                        return;
                }


                //what to do if index is the first element
                if(index == 0){
                        temp.setNext(front);
                        front = temp;
                        current = front;
                        size++;
                        return;
                }


                //finds the node before the position you want to insert
                while(i != (index-1)){
                        x = x.getNext();
                        i++;

                }
                if(x.getNext() == null)
                {
                        rear.setNext(temp);
                        rear = rear.getNext();
                        current = rear;
                        size++;
                        return;
                }
                temp.setNext(x.getNext());
                x.setNext(temp);
                current = temp;
                size++;

        }

        /**
         * Removes the element at the specified position in this list.
         *
         * @param index the index of the element to be removed
         *
         * @return the element previously at the specified position
         *
         * @throws IndexOutOfBoundsException if the index is out of range
         * (index < 0 || index >= size())
         */
        @Override
        public String remove(int index) throws IndexOutOfBoundsException{

                //set a Node to the front
                Node x = front;
                //a is used to save the data of the deleted element
                String a;
                //i is an iterator
                int i = 0;

                //throws an exception if index is out of bounds
                if((index < 0 || index >= size()))
                        throw new IndexOutOfBoundsException();

                //Do this if removing the first
                if(index == 0){
                        //save the deleted string
                        a = front.getData();
                        front = front.getNext();
                        current = front;
                        size--;
                        //return the string
                        return a;
                }


                //This is done if the element is in the middle or last of list
                while(i != (index-1)){
                        x = x.getNext();
                        i++;
                }

                a = x.getNext().getData();
                x.setNext(x.getNext().getNext());
                current = x;
                size--;

                //Sets the new current line
                if(x.getNext() != null)
                        current = x.getNext();
                else
                        current = x;

                if(a.equals(null))
                        return null;
                else
                        return a;
        }

        /**
         * Returns the element at the specified position in this list.
         * Return null if the index is out of range
         * (index < 0 || index >= size())
         *
         * @param index index of the element to return
         *
         * @return the element at the specified position in this list
         *
         * @throws IndexOutOfBoundsException if the index is out of range
         * (index < 0 || index >= size())
         */
        @Override
        public String get(int index) throws  IndexOutOfBoundsException{
                if(index < 0 || index >= size())
                        throw new IndexOutOfBoundsException();
                int i = 0;
                Node x = front;
                while(i != index){
                        x = x.getNext();
                        i++;

                }

                return x.getData();

        }

        /**
         * Returns true if this list contains no elements.
         *
         * @return true if this list contains no elements and false
         * otherwise
         */
        @Override
        public boolean isEmpty() {
                // ALREADY DONE FOR YOU :-)
                return size == 0;
        }

        /**
         * Returns the number of elements in this list.
         *
         * @return the number of elements in this list
         */
        @Override
        public int size() {
                // ALREADY DONE FOR YOU :-)
                return size;
        }

        /**
         * Removes all of the elements from this list. The list will be
         * empty after this call returns.
         */
        @Override
        public void clear() {
                // ALREADY DONE FOR YOU :-)
                front = null;
                rear = null;
                current = null;
                size = 0;
        }

        /**
         * Sets the current to a new index.
         *
         * @param i is the new of Current
         *
         */

        public void setCurrent(int i){

                if(i == 0){
                        current = front;
                        return;
                }

                //creates a temporary node
                Node temp = front;
                int x = 0;
                //loops to where i is
                while(!(x == i))
                {
                        temp = temp.getNext();
                        x++;
                }
                //sets current to temp
                current = temp;
        }


        /**
         * Gets the current node.
         *
         * @return the current node.
         */
        public Node getCurrent(){
                return current;
        }


        /**
         * Gets the index of the current node.
         *
         * @return the current node index.
         */
        public int getCurrentNbr(){
                Node temp = front;
                int i = 0;
                while(current != temp){
                        temp = temp.getNext();
                        i++;
                }
                return i;
        }

        /**
         * Gets the node from the index passed in.
         *
         * @param the index of the node you want to get.
         * @return the node.
         */

        public Node getNode(int index) throws IndexOutOfBoundsException{
                if(index < 0 || index >= size())
                        throw new IndexOutOfBoundsException();
                int i = 0;
                Node x = front;
                while(i != index){
                        x = x.getNext();
                        i++;

                }

                return x;

        }


}