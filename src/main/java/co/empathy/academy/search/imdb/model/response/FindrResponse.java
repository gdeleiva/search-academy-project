package co.empathy.academy.search.imdb.model.response;

import java.util.ArrayList;
import java.util.List;

public class FindrResponse {
    String[] genres;
    String[] directors;
    List<Swipe> swipes;

    List<String[]> data = new ArrayList<>();
    public FindrResponse(List<String[]> data){
        this.data = data;
        genres = new String[10];
        directors = new String[10];
        calculateFndr();
    }

    public String[] getGenres(){
        return genres;
    }

    public String[] getDirectors(){
        return directors;
    }

    public List<Swipe> getSwipes(){
        return swipes;
    }

    public void calculateFndr(){
        int position = 0;
        for(String[] s: data)
        {
            if(s[3].equals(Swipe.LIKE)){
                genres[position] = s[1];
                position++;
            }
            if(s[3].equals(Swipe.Superlike)){
                genres[position] = s[1];
                directors[position] = s[2];
                position++;
            }
        }
    }
}
