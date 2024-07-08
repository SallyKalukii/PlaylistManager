public class Song{  


    //attributes 
    private String title;
    private String artist;
    private double duration; 

    //overloaded constructor 
    public Song(String title, String artist, double duration){
        this.title = title;
        this.artist = artist; 
        this.duration = duration;
    }

    //getter methods for the attributes above 
    public String getTitle(){
        return this.title;
    }

    public String getArtist(){
        return this.artist;
    }

    public double getDuration(){
        return this.duration; //the user will enter the duration of the songs as a double
    }

    //to string method to provide a proper string presentation during display time when presented as decimals eg. 1.45
     public String toString(){
        int minutes = (int) (duration*100)/100 ; //ensuring that the minutes is displayed correctly 
        int seconds = (int) (duration*100) % 100; //ensuring that the seconds is displayed correctly 
        return "Title: " + title + ", Duration: " + minutes + "m " + seconds + "s";
    }


}