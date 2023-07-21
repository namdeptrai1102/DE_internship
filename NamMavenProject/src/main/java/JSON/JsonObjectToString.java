package JSON;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class JsonObjectToString {
	public static void main(String[] args) {
    JsonObject job = new JsonObject();
    job.addProperty("Name","Deadlift");
    //job.addProperty("Name","Dead");
    job.addProperty("Reps",15);
    
    Gson gson = new Gson();
    String jobString = gson.toJson(job);
    
    System.out.println(jobString);
	}
}