package Utility;

public class PostData_Pojo {
	// Pojo -Plain old javaobject

	String name;
	String job;

	public PostData_Pojo() {

	}

	public PostData_Pojo(String name, String job) {
		this.name = name;
		this.job = job;
	}

	// Getter and setter method
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}