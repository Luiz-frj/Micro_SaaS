package br.edu.ifsp.MicroSaaS.model;

import java.time.LocalDate;

public class Agendamento {
	private int id;
	private int id_servico;
	private int id_cliente;
	private int status_agendamento;
	private String horario;
	
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
	public int getId_cliente() {
		return id_cliente;
	}
	private void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getStatus_agendamento() {
		return status_agendamento;
	}
	private void setStatus_agendamento(int status_agendamento) {
		this.status_agendamento = status_agendamento;
	}
	public String getHorario() {
		return horario;
	}
	private void setHorario(String horario) {
		this.horario = horario;
	}
	
	public LocalDate getData() {
		return LocalDate.parse(horario.split(" ")[0]);
	}
	
	public String getTrueStatusAgendamento() {
		switch(status_agendamento) {
			case 0: 
				return "Em espera";
			case 1:
				return "Ativo";
			case 2:
				return "Finalizado";
			case 3:
				return "Cancelado";
			default:
				return "Erro";
		}
	}
	
	public Agendamento(int id, int id_servico, int id_cliente, int status_agendamento, String horario) {
		setId(id);
		setId_servico(id_servico);
		setId_cliente(id_cliente);
		setStatus_agendamento(status_agendamento);
		setHorario(horario);
	}
	
	public Agendamento(int id_servico, int id_cliente, int status_agendamento, String horario) {
		setId_servico(id_servico);
		setId_cliente(id_cliente);
		setStatus_agendamento(status_agendamento);
		setHorario(horario);
	}
	
	@Override
	public String toString() {
		return "Agendamento [id=" + id + ", id_servico=" + id_servico + ", id_cliente=" + id_cliente
				+ ", status_agendamento=" + status_agendamento + ", horario=" + horario + "]";
	}
}
