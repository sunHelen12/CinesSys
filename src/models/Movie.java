package models;

public class Movie {
	private static int _idGenerator = 1;
	private int id;
	private String title;
	private String genre;
	private int duration;
	private String classification;
	private String synopsis;
	
	public Movie(String title, String genre, int duration, String classification, String synopsis) {
		this.title = title;
		this.genre = genre;
		this.duration = duration;
		this.classification = classification;
		this.synopsis = synopsis;
		id=_idGenerator++;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String toString() {
		return " Movie id=" + id + 
				"\nTitle=" + title + 
				"\nGenre=" + genre +
				"\nDuration=" + duration+
				"\nClassification=" + classification + 
				"\nSynopsis=" + synopsis ;
	}
	
	
	
	
}
