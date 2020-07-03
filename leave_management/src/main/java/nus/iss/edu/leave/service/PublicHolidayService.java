package nus.iss.edu.leave.service;

import java.util.List;

import nus.iss.edu.leave.model.PublicHoliday;


public interface PublicHolidayService {
	
		public void addPublicHoliday(PublicHoliday publicholiday);
		public void deletePublicHoliday(PublicHoliday publicholiday);
		public List<PublicHoliday> ListPublicHoliday();

}
