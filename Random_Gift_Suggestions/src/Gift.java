import java.util.ArrayList;
import java.util.List;


public class Gift {

	public String title;
	public List<Place> placesToGet;
	public String wish;
	
	public Gift(){
		this.placesToGet = new ArrayList<Place>();
	}	
	
	public Gift(String title, List<Place> placesToGet){
		this();
		this.title = title;
		this.placesToGet= placesToGet;
	}
}
