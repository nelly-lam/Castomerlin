package code;

public class Materials {
	
	private String name;
	private String fileImage;
	
	public Materials(String name, String file) {
		this.setName(name);
		this.fileImage = file;
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getFileImage() {return fileImage;}
	public void setFileImage(String file) {this.fileImage = file;}
}
