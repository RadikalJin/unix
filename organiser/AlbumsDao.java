import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;

/** Assumes UTF-8 encoding. JDK 7+. */
public class ReadWithScanner {

  public static void main(String... aArgs) throws IOException {
    ReadWithScanner parser = new ReadWithScanner("albumsByDate");
    parser.processLineByLine();
    log("Done.");
  }
  
  /**
   Constructor.
   @param aFileName full name of an existing, readable file.
  */
  public ReadWithScanner(String aFileName){
    fFilePath = Paths.get(aFileName);
  }
  
  
  /** Template method that calls {@link #processLine(String)}.  */
  public final void processLineByLine() throws IOException {
	Map<String, AlbumRecord> map = new HashMap<String, AlbumRecord>();
    try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
      while (scanner.hasNextLine()){
        AlbumRecord record = processLine(scanner.nextLine());
	map.put(record.getDate(), record);
      }      
    }
	log("Got result from map for March 13: " + map.get("March 13").getArtist());
	log("Got result from map for May 19: " + map.get("May 19").getArtist());
	log("Got result from map for July 31: " + map.get("July 31").getArtist());
  }
  
  /** 
   Overridable method for processing lines in different ways.
    
   <P>This simple default implementation expects simple name-value pairs, separated by an 
   '=' sign. Examples of valid input: 
   <tt>height = 167cm</tt>
   <tt>mass =  65kg</tt>
   <tt>disposition =  "grumpy"</tt>
   <tt>this is the name = this is the value</tt>
  */
  public AlbumRecord processLine(String aLine){
    //use a second Scanner to parse the content of each line 
    Scanner scanner = new Scanner(aLine);
    scanner.useDelimiter("@");
    if (scanner.hasNext()){
      //assumes the line has a certain structure
      String date = scanner.next().trim();
      scanner.useDelimiter("\\|");
      String artist = scanner.next().trim().substring(2);
      String album = scanner.next().trim();
      log("Date is: " + quote(date) + " Artist is: " + quote(artist) + " and Album is: " + quote(album));
	AlbumRecord record = new AlbumRecord(date, artist, album);
	return record; 
    }
    else {
      log("Empty or invalid line. Unable to process.");
	return new AlbumRecord("", "", "");
    }
  }
  
  // PRIVATE 
  private final Path fFilePath;
  private final static Charset ENCODING = StandardCharsets.UTF_8;  
  
  private static void log(Object aObject){
    System.out.println(String.valueOf(aObject));
  }
  
  private String quote(String aText){
    String QUOTE = "'";
    return QUOTE + aText + QUOTE;
  }
}


final class OrganiserEntry {
	private final String entryDate; 
	private final String dueDate; 
	private final String heading; 
	private final String content; 

	public AlbumRecord(String entryDate, String dueDate, String heading, String content) {
		this.entryDate = entryDate;
		this.entryDate = entryDate;
		this.artist = artist;
		this.album = album;
	}

	public String getDate() {
		return date;
	}


	public String getArtist() {
		return artist;
	}

	public String getAlbum() {
		return album;
	}

}
