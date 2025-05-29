package br.edu.ifsp.MicroSaaS.controller.command.logged;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifsp.MicroSaaS.controller.command.Command;
import br.edu.ifsp.MicroSaaS.dao.ServicoDAO;
import br.edu.ifsp.MicroSaaS.dao.factory.EspecialidadeDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.PortifolioDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.PrestadorDAOFactory;
import br.edu.ifsp.MicroSaaS.dao.factory.ServicoDAOFactory;
import br.edu.ifsp.MicroSaaS.model.Portifolio;
import br.edu.ifsp.MicroSaaS.model.Prestador;
import br.edu.ifsp.MicroSaaS.model.Servico;
import br.edu.ifsp.MicroSaaS.dao.EspecialidadeDAO;
import br.edu.ifsp.MicroSaaS.dao.PortifolioDAO;
import br.edu.ifsp.MicroSaaS.dao.PrestadorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoggedHomeCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServicoDAO servicoDao = new ServicoDAOFactory().factory();
		PortifolioDAO portifolioDao = new PortifolioDAOFactory().factory();
		EspecialidadeDAO especialidadeDao = new EspecialidadeDAOFactory().factory();
		
		String local = request.getParameter("local");
		String especialidade_name = request.getParameter("especialidade");
		String page = request.getParameter("page");
		
		List<Servico> servicoList = new ArrayList<>();
		List<Servico> servicos_local = new ArrayList<>();
		List<Servico> servicos_especialidade = new ArrayList<>();
		
		if (local != null && !local.isEmpty()) {
			servicos_local = servicoDao.getByLocal(local, page != null ? Integer.parseInt(page) : 0, 10);
		}
		
		if (especialidade_name != null && !especialidade_name.isEmpty()) {
			servicos_especialidade = especialidadeDao.getByEspecialidade(especialidade_name);
		}
			
		if (!servicos_local.isEmpty()) {
			if (!servicos_especialidade.isEmpty()) {
				servicoList = findDuplicates(servicos_especialidade, servicos_local);
			} else {
				servicoList = servicos_local;
			}
		} else {
			if (!servicos_especialidade.isEmpty()) {
				servicoList = servicos_especialidade;
			} else {
				servicoList = servicoDao.getPage(page != null ? Integer.parseInt(page) : 0, 10);
			}
		}
		
		System.out.println(servicoList);
		
		HashMap<Servico, String> servicoImagem = new HashMap<Servico, String>();
		
		for (Servico s : servicoList) {
			List<Portifolio> portifolio = portifolioDao.getByIdServico(s.getId());
			if (!portifolio.isEmpty()) {
				String splitter = File.separator.replace("\\","\\\\");
				servicoImagem.put(s, portifolio.get(0).getCaminho_img().split(splitter)[portifolio.get(0).getCaminho_img().split(splitter).length - 1]);
			}
		}
		
		request.setAttribute("servico_list", servicoImagem);
		
		return "/logged/logged_home.jsp";
	}
	
	public static List<Servico> findDuplicates(List<Servico> list1, List<Servico> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
