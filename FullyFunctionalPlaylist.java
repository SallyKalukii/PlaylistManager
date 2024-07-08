import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//creating a new node 
class Node2{
    //attributes
    Song song; 
    Node2 next;
    Node2 prev;

    //constructor 
    Node2 (Song song){
        this.song = song; 
    }
}
//creating a doubly linked list class 
public class FullyFunctionalPlaylist{
    //attributes 
    int size = 0;
    Node2 head = null;
    Node2 tail = null; 
    Node2 temp = null; 

    //a method to display the nodes in the list
    public void display(){
        temp = head; //temporary variable 
        while (temp != null){  //traverses through the list 
            System.out.println(temp.song + " "); 
            temp = temp.next; //moves onto the next node 
        }
    }

    //method to add nodes to the end of the playlist 
    public void addToEnd(Song song){
        Node2 n2 = new Node2(song); //creating a new instance of the node 
        //if empty 
        if (head == null){
            head = n2; //setting head to n1 when adding the first node 
            tail = n2; //setting tail to n1 when adding the first node 
        }
        else{ //adding the node onto the last node which is tail 
            tail.next = n2; 
            n2.prev = tail;
            tail=n2;
            size++; //incrementing the size of the node 

        }
    }
    //method to add an element at a specific position
    public void addtoSpecificPosition(int position, Song song){
        Node2 n2 = new Node2(song); //create a new node instance 
        n2.song = song; 
        n2.next = null; 

        //acounts for the index out of bound error 
        if (position <= 0 || position > size + 1) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        if (position == 1){ //inserting a node in the first position 
            n2.next = head; //connecting the linked field of the new node onto the head 
            head.prev = n2; //connecting the prev linked field of the head to the new node 
            head = n2; //making the new node the head 
            size++; //incrementing the size of the playlist 
        }
        else if (position == (size+1)){ //adding the node in the last position 
            tail.next = n2; //conecting the next of the last node onto the new node 
            n2.prev = tail; //connecting the previous linkfield of the new node onto the tail 
            tail=n2; //making the new node the tail 
            size++; //incrementing the size of the playlist 
        }
        else if (position > 1 && position < (size + 1)){ //inserting a node in the middle of the list 
            int count = 1;
            temp = head; //temporay variable to hold the head 

            while(count < (position-1)){ //moving onto the next node when the count is less than 0
                temp = temp.next; 
                count++; //incrementing the count variable
            }
            n2.next = temp.next; //temp gives the address of the node before while n gives address of the new node 
            n2.prev = n2; 
            temp.next.prev = n2; //making the prev of the linkfield of the current node point to the new node 
            temp.next = n2; //pointing the next onto the new node 
            size++; //increments the size of the new node
        }
        else{
            System.out.println("Invalid position!"); //how about throwing an exception error? 
        }

    }
    //method to remove the node by title 
    public void removeByTitle (String title){
        //check if list is empty 
        if (head == null ){
            System.out.println("List is empty!");
            return; 
        }

        //head node is being removed 
        if(head.song.getTitle().equals(title)){  //checks if the title of the new node is similar to the inputed by the user 
            head = head.next; //removed the particular node 
            if (head != null){
                head.prev = null;  //making the new head's prev be null
            }
            size--; //decrementing the size of the node
            return;
        }
        //initializing temp to head 
        temp = head;
        //traversing through the list to find the node to be removed 

        while (temp != null && !temp.song.getTitle().equals(title )){ //not the last node and the title are not the same
            temp = temp.next;  //moves onto the next node 
        }

        //when the song (temp) is not found 
        if (temp == null){
            System.out.println("The title of the song is not found!");
            return;
        }
        

        //ommitting the temp.next node 
        if (temp.next != null){
            temp.next.prev = temp.prev; 
        }
        if (temp.prev != null){
            temp.prev.next = temp.next;
        }
            
        size--; //decrementing the size of the node
    }
    //method to remove a node by position 
    public void removeByPosition(int position){
        if (position ==1){ //at the fist node in the playlist 
            head = head.next; //removes the first node 
            head.prev = null;  //makes the prev linkfield be null hence n=makes the node officially the head 
            size --; //decrementing the size of the node 
        }
        else if(position == size){ //removing at the last node 
            temp = head; //temporary variable 

            tail = tail.prev; 
            temp.next = null; //deletes the current node 
            size--; //decrements the size of the node 
        }
        else if (position > 1 && position < size){ //removing the nodes in the middle of the list 
            temp = head; //temporary variable 
            int count = 1;

            while (count < position-1 ){ //traverses the list
                temp = temp.next; //moves onto the next node
                count++; //incrementing the count variable 
            }
            temp.next = temp.next.next; //once the node with the specified position is found, we attach its next linked field to the node that suceeds it 
            temp.next.prev = temp; //we connect the current node onto the previous linked field of the node suceeding it
            size--; //decrements the size of the list 
        }
        else{
            System.out.println("Invalid position!");
        }
    }
    //method to calculate the total duration of the playlist 
    public double playlistTotalDuration(){
        double total = 0.0; //initializing the accumulator
        temp = head; //temporary variable

        //iterating through the songs in the playlist
        while (temp != null){ //iterates till it gets to the end of the list 
            total = total + temp.song.getDuration(); //updating the total variable 
            temp = temp.next; //moves onto the next node 
        }

        return total; //returning the total duration 
    }
    //method to play the next song 
    public void nextSong(){
        temp = head; //temporary variable 

        //if the list is empty 
        if (temp == null){
            temp = head; 
            return;
        }
        if (temp.next != null && temp != null){ //if the list is not empty 
            temp = temp.next; //play the next song 
        }
        else{
            System.out.println("you have reached the end of the playlist!");
        }
    }
    //playing the previous song 
    public void previousSong(){
        temp = head; //temporary variable 

        if (temp == null){
            temp = head ; //if list is empty
        }
        else if (temp != null && temp.next != null){ //if the list is not empty 
            temp = temp .prev;  //play the previous song 
        }
        else{
            System.out.println("You have reached the beginning of the playlist!");
        }
        
    }

    //shuffling the playlist 
    public void shuffleSongs(){
        if (head == null){ //ifplaylist is empty, there is nothing to be returned 
            return;
        }

        Random random = new Random(); //to make the shuffling random 
        ArrayList<Node2> n = new ArrayList<>();  //adding the shuffled songs onto an arraylist 

        //converting the linked list into an ArrayList for easier random access 
        temp = head; 
        while (temp!= null){
            n.add(temp); //adding the nodes into the new arrayList called n 
            temp = temp.next; //temporary variable
        }
        //selects elements from the list 
        ArrayList<Song> shuffled = new ArrayList<>();
        while (!n.isEmpty()) {
            int k = random.nextInt(n.size()); //adding the nodes into the new arrayList called n
            Node2 node = n.remove(k); //removes elements from the list 
            shuffled.add(node.song); //adds the elements into the new shuffled List 
        }

        //I have to rebuild the linked list with shuffled songs 
        head = null ;
        tail = null; 
        size = 0;

        for (Song song:shuffled){ //iterating through the shuffled ArrayList
            addToEnd(song); //adding the songs that are shuffled onto the arraylist 
        
        }

        

    }
    //check insert and delete methods 
    //method to create a continuous play mode where, after the last song, the playlist starts again from the beginning 
    public void countinousPlayMode(){
        temp = head; 
        //initialize head variable 
        if (temp == null) {
            temp = head;  // Start at the beginning if no song is currently being played
        } 
        else {
            temp = temp.next; // moves to the next song 
            if (temp == null) {  // looping back to the beginning once we reach the end of the playlist 
                temp = head;
            }
        }
    }

    //method for adding an option to toggle between continous play and normal play 
    public void toggle() {
        boolean continuousMode; 
        continuousMode = false; //initializing the continuous play to false 
        continuousMode =! continuousMode; //if true 
        if (continuousMode) {
            System.out.println("Continuous play mode enabled.");
        } else { //if false 
            System.out.println("Normal play mode enabled.");
        }
    }

    public static void main(String[] args){
    
        // Creating a BasicPlaylist instance
        FullyFunctionalPlaylist playlist3 = new FullyFunctionalPlaylist();

        // Adding songs to the playlist
        playlist3.addToEnd(new Song("Thinking Bout You", "Frank Ocean", 3.5));
        playlist3.addToEnd(new Song("Sure Thing", "Miguel", 3.2));
        playlist3.addToEnd(new Song("Best Part", "HER", 3.3));

        // Displaying the playlist
        System.out.println("Playlist:");
        playlist3.display();

        // Adding a song at a specific position
        playlist3.addtoSpecificPosition(2, new Song("Cross", "Mhalangu", 10.1));

        // Displaying the updated playlist
        System.out.println("\nUpdated Playlist:");
        playlist3.display();

        // Removing a song by title
        //using scanner to receive input for the title of song to be removed 
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter the title of the song to remove: ");
        String titleToRemove = scanner.nextLine();
        playlist3.removeByTitle(titleToRemove);

        // Displaying the playlist after removal
        System.out.println("\nPlaylist after removal:");
        playlist3.display();

        // Playing the next song
        System.out.println("\nPlaying next song:");
        playlist3.nextSong();
        //playlist3.display();

        // Playing the previous song
        System.out.println("\nPlaying previous song:");
        playlist3.previousSong();
        //playlist3.display();

        // Shuffling the playlist
        System.out.println("\nShuffling the playlist:");
        playlist3.shuffleSongs();
        playlist3.display();

        //add all the durations in the songs in the playlist using 
        double total= playlist3.playlistTotalDuration(); 
        System.out.println("The playlist's total duration is" + total);

        // Testing continuous play mode
        System.out.println("\nTesting continuous play mode:");
        playlist3.countinousPlayMode();  // First song
        playlist3.countinousPlayMode();  // Next song
        playlist3.countinousPlayMode();  // Next song
        playlist3.countinousPlayMode();  // Loop back to the first song

        // Testing toggle
        System.out.println("\nTesting toggle between continuous and normal play mode:");
        playlist3.toggle();  // Enable continuous play
        

    }


}
    