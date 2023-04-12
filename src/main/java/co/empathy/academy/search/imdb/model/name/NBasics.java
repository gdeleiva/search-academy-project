package co.empathy.academy.search.imdb.model.name;

import co.empathy.academy.search.imdb.model.Name;

import java.sql.Date;

public class NBasics implements Name {
    // Contains the following information for names:
    public String nconst; // nconst (string) - alphanumeric unique identifier of the name/person
    public String primaryName; // primaryName (string)– name by which the person is most often credited
    public Date birthYear; // birthYear – in YYYY format
    public Date deathYear; // deathYear – in YYYY format if applicable, else '\N'
    public String[] primaryProfession; // primaryProfession (array of strings)– the top-3 professions of the person
    public String[] knownForTitles; // knownForTitles (array of tconsts) – titles the person is known for
}
