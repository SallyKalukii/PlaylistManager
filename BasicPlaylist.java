import java.util.Scanner;
//creating a new node 
class Node{
    //attributes for the new node 
    Song song;
    Node next;

    //a constructor for the new node 
    public Node(Song song){
        this.song = song;
        this.next = null;
    }

}
//creating a single linkedList class 
public class BasicPlaylist{
    //attributes 
    int size = 0 ;
    Node head = null; 

    //method to check of the playlist is empty 
    public boolean isEmpty(){
        if(size == 0){//you can also say head == null  
            return true; 
        }
        else{
            return false; 
        }
    }
    //method to check the size of the playlist 
    public int listSize(){
        return size; 
    }


    //method to add a song to the end of the playlist 
    public void addSongToEnd (Song song){
        Node n = new Node(song); //creating an instance of the new Node 

        if (head == null){ // represents the first node in the list 
            head = n;  //assigning the new instance of the new node to the head 
        }
        else{ //satisfies for the end of the node by adding a new node to the end of the list 
            Node temp = head; //temporary variable 
            while (temp.next != null){ //transversing across the list to the end of the list. 
                temp = temp.next; //updating by moving to the next node 
            }
            temp.next = n; //adding the new node to the temp.next that refers the end of the node 
            size++; //incrementing the size of the list 
        }
    }

    //method to add an element at a specific position 
    public void addToSpecificPosition (int position, Song song){
        Node n = new Node(song); //create a new node 

        //accounts for the index out of bound error
        if (position <= 0 || position > size + 1) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        else if (position == 1){ //inserting a node in the first position 
            n.next = head; //connecting the link field of the new node to the head 
            head = n; //making the new node the head  
            size++; //incrementing the size of the node after adding the new node 
        }
        else if (position > 1 && position < (size + 1)){ //inserting a new node at the end of the list 
            int count = 1; //initializing count variable to be used for iteration 
            Node temp = head; //creating a temporary variable to hold the head 

            while(count < (position-1)){ //transversing through the list to the end of the node using the index which is position-1 
                temp = temp.next; //moving onto the next node
                count++; //incrementing the initialized count 
            }
            n.next = temp.next; //temp gives the address of the node before while n gives address of the new node 
            temp.next = n; //attaching the link fields of the new node to the last node in the list 
            size++; //incrementing the size of the list 
        }

    }
    
    //method to remove a song by title
    public void removeByTitle (String title){
        //check if empty 
        if (head == null ){ 
            return; 
        }

        if(head.song.getTitle().equals(title)){ //checking whether the title inputed by the user is similar to the onces in the playlist 
            head = head.next; //if the node was the head, it will be removed and the next node will become the head 
            size--; //decrementing the size of the playlist 
            return;
        }
            //traversing the list to find the node with the matching title 
            Node temp = head;
        
            while (temp.next != null && !temp.next.song.getTitle().equals(title )){ //not the last node and the title are not the same
                temp = temp.next; //moves to the next node of the titles dont match and if we havenot reached the end of the list 
            }
            //if the song with similar title is found then we remove it 
            if (temp.next != null && temp.next.song.getTitle().equals(title)){
                temp.next = temp.next.next; //moves to the node next to the one being removed 
                size--; //decrementing the size of the list 
            }
            else{
                // case where song with title was not found
                System.out.println("Song with title '" + title + "' not found in the playlist.");
            }
            
            
 
        
    }
    //method for removing a song using position from the playlist 
    public void removeByPosition(int position) {
        if (position ==1){ //removing from the first node 
            head = head.next; //selecting the next node as the head  
            size --; //decrementing the size of the list 
        }
        else if(position == size){ //removing the last node 
            Node temp = head; //initializing a temporary variable 
            int count = 1; 
            while (count < position-1){ //transversing until the end of the list (where the count is less than the index)
                temp = temp.next;
                count++; //incrementing the count variable 
            }
            temp.next = null; //if the count is no longer less than the index, then we remove the last node 
            size--; //decrementing the size of the list 
        }
        else if (position > 1 && position < size){ //removing in the middle of the node 
            Node temp = head; //initializing a temporary variable to hold the head 
            int count = 1; 

            while (count < position-1 ){ //traversing through the list from the beginning till the end 
                temp = temp.next; //moves onto the next node 
                count++; //increments the count variable 
            }
            temp.next = temp.next.next; //once we have reached the desired position, we link the node to be removed linkfield to the node that suceeds the one to be removed 
            size--; //decrementing the size of the node 
        }
        else{
            System.out.println("Invalid position!"); //no position found 
        }
    }

    //method to display the nodes in the list 
    public void display(){
        Node temp = head; //temporary variable 
        while (temp != null){  //traverses through the list 
            System.out.println(temp.song + " "); //provide a display for the nodes 
            temp = temp.next; //moves on to the next node 
        }
    }

    //method to calculate the total duration of the playlist 
    public double playlistTotalDuration(){
        double total = 0.0; //initializing the accumulator variable 
        Node temp = head; //temporary variable 

        //iterating through the songs in the playlist
        while (temp != null){ //iterates till it gets to the end of the list 
            total = total + temp.song.getDuration(); //updating the total variable 
            temp = temp.next; //moves onto the next node. 
        }

        return total; //returning the total duration 
    }

    public static void main(String[] args){
    
        // Creating a BasicPlaylist instance
        BasicPlaylist playlist1 = new BasicPlaylist();

        // Adding songs to the playlist
        playlist1.addSongToEnd(new Song("Differences", "Ginuwine", 3.5));
        playlist1.addSongToEnd(new Song("So Gone", "Monica", 4.2));
        playlist1.addSongToEnd(new Song("You", "Lil Wayne", 2.3));

        // Displaying the playlist
        System.out.println("Playlist:");
        playlist1.display();

        // Adding a song at a specific position
        playlist1.addToSpecificPosition(2, new Song("HuMan", "Greentea Peng", 3.1));

        // Displaying the updated playlist
        System.out.println("\nUpdated Playlist:");
        playlist1.display();

        // Removing a song by title
        //using scanner to receive input for the title of song to be removed 
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the title of the song to remove: ");
        String titleToRemove = scanner.nextLine();
        playlist1.removeByTitle(titleToRemove);

        // Displaying the playlist after removal
        System.out.println("\nPlaylist after removal:");
        playlist1.display();


        //add all the durations in the songs in the playlist using 
        double total= playlist1.playlistTotalDuration(); 
        System.out.println("The playlist's total duration is" + total);


    }
}
