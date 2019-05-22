package entities;

public enum Role {
	candidate,coach,enterpriseAdmin,enterpriseRH,enterpriseManager;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
