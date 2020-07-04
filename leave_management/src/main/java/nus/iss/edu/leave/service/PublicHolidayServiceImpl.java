package nus.iss.edu.leave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nus.iss.edu.leave.model.PublicHoliday;
import nus.iss.edu.leave.repo.PublicHolidayRepository;



public class PublicHolidayServiceImpl {
	

	@Autowired
	PublicHolidayRepository holiRepo;
	
	@Transactional
	public void addPublicHoliday(PublicHoliday publicholiday) {
		 holiRepo.save(publicholiday);
	}
	
	@Transactional
	public void deletePublicHoliday(PublicHoliday publicholiday) {
		 holiRepo.delete(publicholiday);
	}
	
	@Transactional
	public List<PublicHoliday> ListPublicHoliday() {
		return holiRepo.findAll();
	}	

}
