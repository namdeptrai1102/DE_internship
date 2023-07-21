package JSON;

import com.google.gson.Gson;

public class ParseJson {

	public static void main(String[] args) {
		Exercise exercise = new Exercise();
        exercise.setName("Squat");
        exercise.setReps(30);

        //serialize
        Gson gson = new Gson();
        String json = gson.toJson(exercise);

        System.out.println("Json Object: " + json);

        Exercise deserialized = gson.fromJson(json, Exercise.class);
        System.out.println("Name: " + deserialized.getName());
        System.out.println("Reps: " + deserialized.getReps());
    }


}
