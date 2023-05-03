package co.empathy.academy.search.imdb.model.response;

import java.util.ArrayList;
import java.util.List;

public class FindrResponse {
    String[] genres;
    String[] directors;
    List<Swipe> swipes;

    String[] data;
    public FindrResponse(String[] data){
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
        for(int i=0; i<20;i+=4)
        {
            if(data[i+3].equals(Swipe.LIKE)){
                genres[position] = data[1];
                position++;
            }
            if(data[i+3].equals(Swipe.Superlike)){
                genres[position] = data[1];
                directors[position] = data[2];
                position++;
            }
        }
    }
}
