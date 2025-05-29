package br.edu.ifsp.MicroSaaS.model;

public class Servico {
	private int id;
	private int id_prestador;
	private String name;
	private String description;
	private int status_servico;
	private String local;
	private int tempo_servico;
	
	public int getTempo_servico() {
		return tempo_servico;
	}
	private void setTempo_servico(int tempo_servico) {
		this.tempo_servico = tempo_servico;
	}
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
	public Servico(int id_prestador, String name, String description, int status_servico, String local, int tempo_servico) {
		setId_prestador(id_prestador);
		setName(name);
		setDescription(description);
		setStatus_servico(status_servico);
		setLocal(local);
		setTempo_servico(tempo_servico);
	}
	
	public Servico(int id_servico, int id_prestador, String name, String description, int status_servico, String local, int tempo_servico) {
		setId(id_servico);
		setId_prestador(id_prestador);
		setName(name);
		setDescription(description);
		setStatus_servico(status_servico);
		setLocal(local);
		setTempo_servico(tempo_servico);
	}
	
	@Override
	public String toString() {
		return "Servico [id=" + id + ", id_prestador=" + id_prestador + ", name=" + name + ", description="
				+ description + ", status_servico=" + status_servico + ", local=" + local + "]";
	}
	
}
