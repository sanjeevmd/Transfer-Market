package transfermarketproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Watch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import transfermarketproject.service.*;
import transfermarketproject.DAO.IManager;
import transfermarketproject.DAO.IPlayer;
import transfermarketproject.DAO.IloginUser;
import transfermarketproject.Model.Manager;

@Controller
@RequestMapping(value="/Manager")
public class ManagerController {
	
	@Autowired
	@Qualifier("ManagerService")
	IManager iManagerService;
	
	@Autowired
	@Qualifier("PlayerService")
	IPlayer iplayerService;

	@RequestMapping(value = "/ViewTeam", method= RequestMethod.GET)
	public ModelAndView viewTeam(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			Manager manager = (Manager) session.getAttribute("user");
			if(session.getAttribute("user") == null) {
				return 	new ModelAndView("redirect:/Index/User");	
			}
			List<Player> players = iManagerService.getAllPlayers(manager.getTeam().getId());
			ModelAndView mv = new ModelAndView("Manager/ViewTeam");
			mv.addObject("players", players);
			return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
		
	}
	
	@RequestMapping(value = "/WatchPlayers", method = RequestMethod.GET)
	public ModelAndView WatchPlayers(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		ModelAndView mv = new ModelAndView("Manager/WatchPlayers");
		Manager manager = (Manager) session.getAttribute("user");
		List<Watch> watch = iManagerService.viewWatch(manager.getId());
		mv.addObject("players",watch);
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	@RequestMapping(value = "/accept", method= RequestMethod.POST)
	public ModelAndView accept(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		String playerID = request.getParameter("selectedPlayer");
		Player player = iManagerService.getPlayer( Integer.parseInt(playerID));
		player.setBidLastDate(null);
		player.setBasePrice(player.getCbAmount());
		player.setCbAmount(null);
		player.setTeam(player.getCbTeam());
		player.setCbTeam(null);
		player.setAvailable(true);
		iplayerService.applyforBid(player);
		return new ModelAndView("redirect:/Manager/CurrentPlayersBid");
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}

	
	@RequestMapping(value = "/movePlayer", method= RequestMethod.GET)
	public ModelAndView movePlayer(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		int playerId = Integer.parseInt(request.getParameter("selectedPlayer"));
		String startDateStr = request.getParameter("bidLastDate");
		Float bidAmount = Float.parseFloat(request.getParameter("basePrice"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(startDateStr);
			iManagerService.makePlayerAvailable(playerId, date, bidAmount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ModelAndView mv = new ModelAndView("redirect:/Manager/ViewTeam");
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/ViewAvailablePlayers", method= RequestMethod.GET)
	public ModelAndView ViewAvailablePlayers(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		List<Player> players = iManagerService.getOpenPlayers(manager.getTeam().getId());
		ModelAndView mv = new ModelAndView("Manager/ViewAvailable");
		mv.addObject("players", players);
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/CurrentPlayersBid", method= RequestMethod.GET)
	public ModelAndView CurrentPlayersBid(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		List<Player> players = iManagerService.getPlayersOnBid(manager.getTeam().getId());
		ModelAndView mv = new ModelAndView("Manager/CurrentPlayersBid");
		mv.addObject("players", players);
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/SearchPlayers", method= RequestMethod.GET)
	public ModelAndView SearchPlayers(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		String search = request.getParameter("Search");
		List<Player> players = iManagerService.getAllPlayers(manager,search);
		ModelAndView mv = new ModelAndView("Manager/SearchPlayers");
		mv.addObject("players", players);
		return mv;
		}
		catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	@RequestMapping(value = "/AddWatch", method= RequestMethod.GET)
	public ModelAndView AddWatch(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		String search = request.getParameter("selectedPlayer");
		iManagerService.addWatch(manager, Integer.parseInt(search));
		ModelAndView mv = new ModelAndView("redirect:/Manager/SearchPlayers");
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/RemoveWatch", method= RequestMethod.GET)
	public ModelAndView RemoveWatch(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		String search = request.getParameter("selectedPlayer");
		iManagerService.deleteWatch(Integer.parseInt(search));
		ModelAndView mv = new ModelAndView("redirect:/Manager/WatchPlayers");
		return mv;}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value = "/bidPlayer", method= RequestMethod.POST)
	public ModelAndView bidPlayer(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		String playerI = request.getParameter("selectedPlayer");
		String bidAmount = request.getParameter("bidAmount");
		iManagerService.updateBid(Integer.parseInt(playerI), Float.parseFloat(bidAmount), manager);
		ModelAndView mv = new ModelAndView("redirect:/Manager/ViewAvailablePlayers");
		return mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	
	@RequestMapping(value="/withdraw", method=RequestMethod.POST)
	public ModelAndView withdraw(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		String playerI = request.getParameter("selectedPlayer");
		Player player = iManagerService.getPlayer(Integer.parseInt(playerI));
		player.setCbAmount(null);
		player.setBidLastDate(null);
		player.setAvailable(true);
		player.setAccepted(true);
		iplayerService.applyforBid(player);
		ModelAndView mv = new ModelAndView("redirect:/Manager/ViewTeam");
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
		try {
		session.removeAttribute("user");
		session.removeAttribute("userPassword");
		return 	new ModelAndView("redirect:/Index/User");
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
	

	@RequestMapping(value = "/Statistics", method= RequestMethod.GET)
	public ModelAndView Statistics(HttpServletRequest request, 
	        HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
		Manager manager = (Manager) session.getAttribute("user");
		
		List<Player> players = iManagerService.getAllPlayers(manager.getTeam().getId());
		float marketVal = 0;
		if(players != null) {
			for (Player player : players) {
				marketVal = marketVal + player.getBasePrice();
			}
		}		
		ModelAndView mv = new ModelAndView("/Manager/Statistics");
		mv.addObject("marketVal",marketVal);
		return 	mv;
		}catch(Exception e) {
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");	
		}
	}
}