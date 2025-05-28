package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.PortifolioDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggedHomeCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServicoDAO servicoDao = new ServicoDAOFactory().factory();
		PortifolioDAO portifolioDao = new PortifolioDAOFactory().factory();
		
		List<Servico> servicoList = servicoDao.getPage(0, 10);
		HashMap<Servico, String> servicoImagem = new HashMap<Servico, String>();
		
		for (Servico s : servicoList) {
			List<Portifolio> portifolio = portifolioDao.getByIdServico(s.getId());
			if (!portifolio.isEmpty()) {
				servicoImagem.put(s, portifolio.get(0).getCaminho_img().split(File.separator)[portifolio.get(0).getCaminho_img().split(File.separator).length - 1]);
			}
		}
		
		request.setAttribute("servico_list", servicoImagem);
		
		return "/logged/logged_home.jsp";
	}

}
