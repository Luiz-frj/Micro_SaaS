package br.edu.ifsp.MicroSaaS.model;

public class Disponibilidade {
	private int id;
	private int id_servico;
	private int dia_semana;
	private String comeco_descanso;
	private String fim_descanso;
	private String inicio_servico;
	private String fim_servico;
	
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public int getId_servico() {
		return id_servico;
	}
	private void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public int getDia_semana() {
		return dia_semana;
	}
	private void setDia_semana(int dia_semana) {
		this.dia_semana = dia_semana;
	}
	public String getComeco_descanso() {
		return comeco_descanso;
	}
	private void setComeco_descanso(String comeco_descanso) {
		this.comeco_descanso = comeco_descanso;
	}
	public String getFim_descanso() {
		return fim_descanso;
	}
	private void setFim_descanso(String fim_descanso) {
		this.fim_descanso = fim_descanso;
	}
	public String getInicio_servico() {
		return inicio_servico;
	}
	private void setInicio_servico(String inicio_servico) {
		this.inicio_servico = inicio_servico;
	}
	public String getFim_servico() {
		return fim_servico;
	}
	private void setFim_servico(String fim_servico) {
		this.fim_servico = fim_servico;
	}
	
	public Disponibilidade(int id, int id_servico, int dia_semana, String comeco_descanso, String fim_descanso,
			String inicio_servico, String fim_servico) {
		setId(id);
		setId_servico(id_servico);
		setDia_semana(dia_semana);
		setComeco_descanso(comeco_descanso);
		setFim_descanso(fim_descanso);
		setInicio_servico(inicio_servico);
		setFim_servico(fim_servico);
	}
	
	public Disponibilidade(int id_servico, int dia_semana, String comeco_descanso, String fim_descanso,
			String inicio_servico, String fim_servico) {
		setId_servico(id_servico);
		setDia_semana(dia_semana);
		setComeco_descanso(comeco_descanso);
		setFim_descanso(fim_descanso);
		setInicio_servico(inicio_servico);
		setFim_servico(fim_servico);
	}
	@Override
	public String toString() {
		return "Disponibilidade [id=" + id + ", id_servico=" + id_servico + ", dia_semana=" + dia_semana
				+ ", comeco_descanso=" + comeco_descanso + ", fim_descanso=" + fim_descanso + ", inicio_servico="
				+ inicio_servico + ", fim_servico=" + fim_servico + "]";
	}
}
