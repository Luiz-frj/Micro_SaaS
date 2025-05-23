package br.edu.ifsp.MicroSaaS.model;

public class Especialidade {
	private int id;
	private String name;
	private String description;
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	
	public Especialidade(int id, String name, String description) {
		setId(id);
		setName(name);
		setDescription(description);
	}
	
	public Especialidade(String name, String description) {
		setName(name);
		setDescription(description);
	}
}
