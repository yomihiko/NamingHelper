package application;

import static application.Define.*;
public class WordSet {
	private String key;
	private String value;
	public WordSet(String key,String value) {

		this.key = key;
		if(this.key == null) {
			this.key = NOTHING;
		}
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	@Override
	public String toString() {
		return value;
	}
}
