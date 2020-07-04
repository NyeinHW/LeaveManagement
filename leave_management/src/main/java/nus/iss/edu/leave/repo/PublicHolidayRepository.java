package nus.iss.edu.leave.repo;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.iss.edu.leave.model.PublicHoliday;


public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, Date> {
	
	@Query(value="select * from public_holiday where date=?1",nativeQuery=true)
	PublicHoliday getPublicHoliday(String date);
	
	@Query(value="select date from public_holiday",nativeQuery=true)
	ArrayList<Date> findAllPublicHolidayDates();
}

