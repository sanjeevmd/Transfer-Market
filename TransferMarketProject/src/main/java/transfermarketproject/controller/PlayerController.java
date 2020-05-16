package transfermarketproject.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import transfermarketproject.DAO.IManager;
import transfermarketproject.DAO.IPlayer;
import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;

@Controller
@RequestMapping(value="/Player")
public class PlayerController {
	
	@Autowired
	@Qualifier("PlayerService")
	IPlayer iplayerService;
	
	@RequestMapping(value = "/MakeAvailable", method= RequestMethod.GET)
	public ModelAndView MakeAvailable(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Player player = (Player) session.getAttribute("user");
		ModelAndView mv ;
		mv= new ModelAndView("Player/MakeAvailable");
		Date date = new Date();
		if(player.getTeam() != null || (player.getBidLastDate() != null && player.getBidLastDate().compareTo(date) > 0) ) {
			mv.addObject("message", "1");
		}
		return mv;}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/apply", method= RequestMethod.POST)
	public ModelAndView apply(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Player player = (Player) session.getAttribute("user");
		ModelAndView mv ;
		String baseAmount = request.getParameter("baseAmount");
		String sdfdate = request.getParameter("bidLastDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(sdfdate);
			player.setBidLastDate(date);
			player.setBasePrice(Float.parseFloat(baseAmount));
			player.setAvailable(false);
			player.setCbAmount(Float.parseFloat(baseAmount));
			iplayerService.applyforBid(player);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/Player/MakeAvailable");	
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/ViewBids", method= RequestMethod.GET)
	public ModelAndView ViewBids(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		ModelAndView mv ;
		Player player = (Player) session.getAttribute("user");
		mv = new ModelAndView("/Player/ViewBids");	
		mv.addObject("player",player);
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/completeSign", method= RequestMethod.POST)
	public ModelAndView completeSign(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		ModelAndView mv ;
		Player player = (Player) session.getAttribute("user");
		if(player.getTeam() == null && player.getCbTeam() != null) {
			player.setAvailable(true);
			player.setAccepted(true);
			player.setBasePrice(player.getCbAmount());
			player.setTeam(player.getCbTeam());
			player.setBidLastDate(null);
			player.setCbAmount(null);
			player.setCbTeam(null);
		}else if(player.getIsAvailable()) {
			player.setAccepted(true);
		}
		iplayerService.applyforBid(player);
		mv = new ModelAndView("redirect:/Player/ViewBids");	
		return mv;
		}
		catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/Logout", method= RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();	
		session.removeAttribute("user");
		session.removeAttribute("userPassword");
		return 	new ModelAndView("redirect:/Index/User");
	}
}
