package JSON;

import com.google.gson.Gson;

public class StringToObjectDirect {
 
    public static void main(String[] args)
    {
        // Converting JSON data into Java String format
        String json = "{\"Name\":\"Bench Press\","+ "\"Reps\":12}";
        Gson gson = new Gson();
        Exercise exercise = gson.fromJson(json, Exercise.class);
        System.out.println(exercise);
        
    }
}