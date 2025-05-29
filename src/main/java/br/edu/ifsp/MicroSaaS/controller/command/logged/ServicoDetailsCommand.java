package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.PortifolioDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;

public class ServicoDetailsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServicoDAO dao = new ServicoDAOFactory().factory();
		PrestadorDAO prestadorDao = new PrestadorDAOFactory().factory();
		PortifolioDAO portifolioDAO = new PortifolioDAOFactory().factory();
		
		String id_servico = request.getParameter("servico");
		Servico servico = dao.getById(Integer.parseInt(id_servico));
		
		if (servico != null) {
			Prestador prestador = prestadorDao.getById(servico.getId_prestador());
			if (prestador != null) {
				List<Portifolio> portifolio = portifolioDAO.getByIdServico(servico.getId());
				List<String> images = new ArrayList<>();
				if (!portifolio.isEmpty()) {
					for (Portifolio p : portifolio) {
						String splitter = File.separator.replace("\\","\\\\");
						images.add(p.getCaminho_img().split(splitter)[p.getCaminho_img().split(splitter).length - 1]);
					}
				}
				request.setAttribute("images", images);
				request.setAttribute("servico", servico);
				request.setAttribute("prestador", prestador);
				return "/logged/servico_details.jsp";
			}
		}
		return "logged.do?action=home";
	}

}
