package br.edu.ifsp.MicroSaaS.model;

public class Servico {
	private int id;
	private int id_prestador;
	private String name;
	private String description;
	private int status_servico;
	private String local;
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public int getId_prestador() {
		return id_prestador;
	}
	private void setId_prestador(int id_prestador) {
		this.id_prestador = id_prestador;
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
	public int getStatus_servico() {
		return status_servico;
	}
	private void setStatus_servico(int status_servico) {
		this.status_servico = status_servico;
	}
	public String getLocal() {
		return local;
	}
	private void setLocal(String local) {
		this.local = local;
	}
	public Servico(int id_prestador, String name, String description, int status_servico, String local) {
		setId_prestador(id_prestador);
		setName(name);
		setDescription(description);
		setStatus_servico(status_servico);
		setLocal(local);
	}
	
	public Servico(int id_servico, int id_prestador, String name, String description, int status_servico, String local) {
		setId(id_servico);
		setId_prestador(id_prestador);
		setName(name);
		setDescription(description);
		setStatus_servico(status_servico);
		setLocal(local);
	}
	
	@Override
	public String toString() {
		return "Servico [id=" + id + ", id_prestador=" + id_prestador + ", name=" + name + ", description="
				+ description + ", status_servico=" + status_servico + ", local=" + local + "]";
	}
	
}
