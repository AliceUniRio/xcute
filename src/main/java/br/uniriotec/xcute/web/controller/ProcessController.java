package br.uniriotec.xcute.web.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.uniriotec.xcute.busines.service.IKipProcessService;


/**
 * Handles requests for the application home page.
 */
@Controller("/")
public class ProcessController  extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);
	
	@Autowired
	private IKipProcessService kipProcessService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "index";
	}
	
	
	@RequestMapping(value = "execution")
	public String showExecutor(Model model){
		return "/execution/execution";
	}
	
	
	@RequestMapping(value = "designer")
	public String showDesigner(Model model){
		return dispatchResponse(model, "/designer/designer");
	}
	
	@RequestMapping(value = "designer/{id}")
	public String show(@PathVariable("id")Integer id, Model model){
       model.addAttribute("idSelected", id);
	   model.addAttribute("kip",kipProcessService.get(id));
	   return dispatchResponse(model, "/designer/designer");
	}
	
	@RequestMapping(value = "designer/recomendation/{id}")
	public String showRecomendation(@PathVariable("id")Integer id, Model model){
       model.addAttribute("idSelected", id);
	   model.addAttribute("kia",kipProcessService.getKia(id)); 
	   return dispatchResponse(model, "/designer/recomendation");
	}
	
	
	public IKipProcessService getKipProcessService() {
		return kipProcessService;
	}

	public void setKipProcessService(IKipProcessService kipProcessService) {
		this.kipProcessService = kipProcessService;
	}

}
