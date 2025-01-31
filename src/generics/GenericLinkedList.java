// Exercise: GenericLinkedList
// Bjørn 28/12 2024

package generics;

public class GenericLinkedList<T> {
    Node head=null;
    Node tail=null;

    public static void main(String[] args) throws LinkedListException {
        System.out.println(new GenericLinkedList<Country>());
        GenericLinkedList<Country> list=new GenericLinkedList<>();
        list.addFirst(new Country("Danmark"));
        list.addFirst(new Country("Norge"));
        list.addFirst(new Country("Sverige"));
        list.addLast(new Country("Tyskland"));
        list.addLast(new Country("Frankrig"));
        list.addLast(new Country("Spanien"));
        System.out.println(list);
//        list.addAtIndex(-1, 0);  // errer
        list.addAtIndex(0, new Country("USA"));        System.out.println(list);
        list.addAtIndex(3, new Country("USA"));        System.out.println(list);
        list.addAtIndex(8, new Country("USA"));        System.out.println(list);
        list.removeFirst();           System.out.println(list);
        list.removeFirst();           System.out.println(list);
        list.removeLast();            System.out.println(list);
        list.removeLast();            System.out.println(list);
        list.removeAtIndex(0);        System.out.println(list);
        list.removeAtIndex(3);        System.out.println(list);
        list.removeAtIndex(1);        System.out.println(list);
//        list.removeAtIndex(2);        System.out.println(list);   // error
        System.out.println(list+" "+list.get(0));
        System.out.println(list+" "+list.get(1));
//        System.out.println(list+" "+list.get(2));   // error

//       new LinkedIntlist<Country>().removeFirst();    // error
//       new LinkedIntlist<Country>().removeLast();     // error
    }

    void addFirst(T value){
        Node n=new Node(value);
        if (head==null) {
            head=n;
            tail=n;
        }
        else {
            head.prev=n;
            n.next=head;
            head=n;
        }
    }

    void addLast(T value){
        Node n=new Node(value);
        if (tail==null) {
            head=n;
            tail=n;
        }
        else {
            tail.next=n;
            n.prev=tail;
            tail=n;
        }
    }

    void addAtIndex(int index, T value) throws LinkedListException {
        // index=0 means first item in the list
        // index=1 means second item in the list - and so on
        if (index<0 || size()<index)
            throw new LinkedListException("Index must be in [0,"+size()+"]");
        if (index==0) {  // we must modify head
            addFirst(value);
        }
        else {
            Node it=head;   // iterator
            for (int i=1; i<index; i++) it=it.next;
            Node n=new Node(value);
            n.prev=it;
            n.next=it.next;
            it.next=n;
            if (n.next!=null)
                n.next.prev=n;
            else
                tail=n;
        }
    }

    void removeFirst() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("List is empty");
        if (size()==1) {
            head=null;
            tail=null;
        }
        else {
            head=head.next;
            head.prev=null;
        }
    }

    void removeLast() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("List is empty");
        if (size()==1) {
            head=null;
            tail=null;
        }
        else {
            tail=tail.prev;
            tail.next=null;
        }
    }

    void removeAtIndex(int index) throws LinkedListException {
        // index=0 means first item in the list
        // index=1 means second item in the list - and so on
        if (index<0 || (size()-1)<index)
            throw new LinkedListException("Index must be in [0,"+(size()-1)+"]");
        if (index==0) {  // we must update head
            removeFirst();
        } else if (index==size()-1) { // we must update tail
            removeLast();
        } else {
            Node it=head;   // iterator
            for (int i=0; i<index; i++) it=it.next;
            it.prev.next=it.next;
            it.next.prev=it.prev;
            it.prev=null;
            it.next=null;
        }
    }

    T get(int index) throws LinkedListException {
        // index=0 means first item in the list
        // index=1 means second item in the list - and so on
        if (index<0 || (size()-1)<index)
            throw new LinkedListException("Index must be in [0,"+(size()-1)+"]");
        Node it=head;   // iterator
        for (int i=0; i<index; i++) it=it.next;
        return it.elm;
    }

    boolean isEmpty(){
        return (head==null);
    }

    int size(){
        int result=0;
        Node n=head;
        while (n!=null){
            result++;
            n=n.next;
        }
        return result;
    }

    public String toString(){
        String result="[";
        Node n=head;
        while (n!=null){
            if (n!=head) result=result+",";
            result=result+n.elm;
            n=n.next;
        }
        result=result+"]"+" ("+size()+")";
        return result;
    }

    class Node {
        Node next=null;
        Node prev=null;
        T elm;
        Node(T value){ elm=value; }
    }

}

class LinkedListException extends Exception {
    String errortext;
    LinkedListException(String text){
        errortext=text;
    }
    public String toString(){
        return "LinkedListException: "+errortext;
    }
}
