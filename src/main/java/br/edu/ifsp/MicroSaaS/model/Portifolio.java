package br.edu.ifsp.MicroSaaS.model;

public class Portifolio {
	private int id_img;
	private int id_servico;
	private String caminho_img;
	
	public int getId_img() {
		return id_img;
	}
	private void setId_img(int id_img) {
		this.id_img = id_img;
	}
	public int getId_servico() {
		return id_servico;
	}
	private void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public String getCaminho_img() {
		return caminho_img;
	}
	private void setCaminho_img(String caminho_img) {
		this.caminho_img = caminho_img;
	}
	
	public Portifolio(int id_img, int id_servico, String caminho_img) {
		setId_img(id_img);
		setId_servico(id_servico);
		setCaminho_img(caminho_img);
	}
	
	public Portifolio(int id_servico, String caminho_img) {
		setId_servico(id_servico);
		setCaminho_img(caminho_img);
	}
	@Override
	public String toString() {
		return "Portifolio [id_img=" + id_img + ", id_servico=" + id_servico + ", caminho_img=" + caminho_img + "]";
	}
}
