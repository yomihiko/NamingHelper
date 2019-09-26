package application;

public class WordSet {
	private String key;
	private String value;
	public WordSet(String key,String value) {
		this.key = key;
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
