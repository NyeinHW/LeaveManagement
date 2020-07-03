package nus.iss.edu.leave.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import nus.iss.edu.leave.model.PublicHoliday;
import nus.iss.edu.leave.repo.PublicHolidayRepository;

@Controller
public class PublicHolidayController {
	@Autowired
	private PublicHolidayRepository holiRepo;

	public void setHoliRepo(PublicHolidayRepository holiRepo) {
		this.holiRepo = holiRepo;
	}
	
	@GetMapping("/list")
	public String getPublicHolidays(Model model)
	{
		model.addAttribute("holidays", holiRepo.findAll());
		return "publicholiday";
	}
	
	@GetMapping("/add")
    public String createPublicHoliday(Model model) {
        model.addAttribute("publicholiday", new PublicHoliday());
        return "publicholidayform";
    }
	
	@RequestMapping(value = "/save", method=RequestMethod.POST)
    public String savePublicHoliday(@ModelAttribute("publicholiday") @Valid PublicHoliday holi, BindingResult bindingresult, Model model) {
		
		if(bindingresult.hasErrors())
		{
			return "publicholidayform";
		}
		System.out.println(holi.getName());
		System.out.println(holi.getDate());
        holiRepo.save(holi);
        return "redirect:/publicholiday/list";
    }
	

    @GetMapping("/delete/{date}")
    public String deletePublicHoliday(@PathVariable(name = "date") String date) {
        holiRepo.delete(holiRepo.getPublicHoliday(date));
        return "forward:/publicholiday/list";
    }

}
