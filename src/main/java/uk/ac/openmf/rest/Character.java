package uk.ac.openmf.rest;

import java.net.URL;

import org.codehaus.jackson.annotate.JsonAutoDetect;


//@XmlRootElement
@JsonAutoDetect
public final class Character {

	private  int id;
	private  String name;
	private  boolean isHuman;
	private URL characterUrl;

	protected Character() {
		
	}
	
	public Character(int id, String name, boolean isHuman, URL characterUrl) {
		super();
		this.id = id;
		this.name = name;
		this.isHuman = isHuman;
		this.characterUrl = characterUrl;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHuman() {
		return isHuman;
	}

	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	
	public URL getCharacterUrl() {
		return characterUrl;
	}

	public void setCharacterUrl(URL characterUrl) {
		this.characterUrl = characterUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
