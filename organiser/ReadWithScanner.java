:mport java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.*;

public class ReadWithScanner {

	public static void main(String... aArgs) throws IOException {
		ReadWithScanner parser = new ReadWithScanner("albumsByDate");
		parser.processLineByLine();
		log("Done.");
	}
  
	public ReadWithScanner(String aFileName){
		fFilePath = Paths.get(aFileName);
	}


	public final void processLineByLine() throws IOException {
		Map<String, AlbumRecord> map = new HashMap<String, AlbumRecord>();
		Map<String, AlbumRecord> mapByArtistName = new HashMap<String, AlbumRecord>();
		
		try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
			while (scanner.hasNextLine()){
				AlbumRecord record = processLine(scanner.nextLine());
				map.put(record.getDate(), record);
				mapByArtistName.put(record.getArtist(), record);
			}      
		}
		log("Got result from map for May 19: " + map.get("May 19").getArtist());

	// THE BELOW IS EXPERIMENTAL	
		Scanner inputScanner = new Scanner(System.in);
                System.out.println("Enter the name of an artist below:");
                String artistName = inputScanner.nextLine();
                inputScanner.close();
                System.out.println(mapByArtistName.get(artistName).toString());
	}

	public AlbumRecord processLine(String aLine) {
		Scanner scanner = new Scanner(aLine);
		scanner.useDelimiter("@");
		if (scanner.hasNext()){
			String date = scanner.next().trim();
			scanner.useDelimiter("\\|");
			String artist = scanner.next().trim().substring(2);
			String album = scanner.next().trim();
			log("Date is: " + quote(date) + " Artist is: " + quote(artist) + " and Album is: " + quote(album));
			AlbumRecord record = new AlbumRecord(date, artist, album);
			return record; 
		} else {
      			log("Empty or invalid line. Unable to process.");
			return new AlbumRecord("", "", "");
		}
	}
  
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


final class AlbumRecord {
	private final String date; 
	private final String artist; 
	private final String album; 

	public AlbumRecord(String date, String artist, String album) {
		this.date = date;
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

	public String toString() {
		return "Release Date: " + date + ", Artist: " + artist + ", Album: " + album;
	}

}

