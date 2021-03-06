package com.quranyeamen.hotel.web.application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quranyeamen.hotel.business.domain.RoomReservation;
import com.quranyeamen.hotel.business.service.ReservationService;

@Controller
@RequestMapping(value="/reservations")
public class ReservationController {
	private static  final  DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(@RequestParam(value ="date", required=false) String dateString, Model model) {
		Date date = null;
		
		if(dateString != null) {
			try 
			{
				date=DATE_FORMAT.parse(dateString);
			}
			catch(ParseException pe ) 
			{
				date = new Date();
			}
		}
		else { 
			date=new Date();
		}
		List<RoomReservation> roomReservationlist = this.reservationService.getRoomReservationsForDate(date.toString());
		model.addAttribute("roomReservation",roomReservationlist );
		return "reservations";
	}
	

}
