package transfermarketproject.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import transfermarketproject.DAO.IloginUser;
import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;

import java.security.Key;
import java.util.List;

@Controller
@RequestMapping(value="/Index")
public class UserController {
	
	@Autowired
	IloginUser authService;
	
	@RequestMapping(value = "/Register", method= RequestMethod.GET)
	public ModelAndView register(HttpServletRequest request, 
	        HttpServletResponse response) {
		List<Team> teams = authService.getTeams();
		ModelAndView mv = new ModelAndView("User/Register");
		mv.addObject("teams", teams);
		return mv;
	}
	
	@RequestMapping(value = "/User", method= RequestMethod.GET)
	public ModelAndView Index(HttpServletRequest request, 
	        HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("User/auth");
		return mv;
	}
	
	@RequestMapping(value = "/Login", method= RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, 
	        HttpServletResponse response) {
		ModelAndView mv;
		try {
 		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		String alternatPass = "";
		HttpSession session = request.getSession();
		if(session.getAttribute("userPassword") != null) {
			alternatPass = session.getAttribute("userPassword").toString();
		}
		String type = request.getParameter("role").trim();
		Player player;
		Manager manager;
		
		if(type.equalsIgnoreCase("player")){
			player = authService.isValidPlayer(username, password, alternatPass);
			if(player != null) {
				mv = new ModelAndView("User/playerLogin");	
				session.setAttribute("user",player);
				session.setAttribute("userPassword", player.getPassword());
				mv.addObject("player", player);
			}else {
				mv = new ModelAndView("redirect:/Index/User");
				mv.addObject("message", "Invalid Credential");
				return mv;
			}
		}else {
			manager = authService.isValidManager(username, password, alternatPass);
			if(manager != null) {
			mv = new ModelAndView("User/managerLogin");
			session.setAttribute("user",manager);
			session.setAttribute("userPassword", manager.getPassword());
			mv.addObject("player", manager);
			}else {
				mv = new ModelAndView("redirect:/Index/User");
				mv.addObject("message", "Invalid Credential");
				return mv;		
			}
		}
		return mv;		
		}catch(Exception e) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			mv = new ModelAndView("redirect:/Index/User");
			return mv;		
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
	
	@RequestMapping(value = "/Save", method= RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, 
	        HttpServletResponse response) {
		try {
		String type = request.getParameter("role").trim();
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String userId = request.getParameter("userID");
		String team = request.getParameter("lists");
		
	    String key = "Bar12345Bar12345";
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b: encrypted) {
            sb.append((char)b);
        }
        
		if(type.equalsIgnoreCase("player")){
			Player player = new Player();
			player.setName(username);
			player.setPassword(sb.toString());
			player.setUserId(userId);
			if(team.equalsIgnoreCase("None")) {
				player.setTeam(null);
			}else {
				Team teams = authService.getTeam(Integer.parseInt(team));
				player.setTeam(teams);
				player.setAvailable(true);
			}
			boolean isSuccess = authService.savePlayer(player);
			if(!isSuccess) {
				ModelAndView mv = new ModelAndView("redirect:/Index/Register");
				mv.addObject("message", "userId already exist");
				return mv;
			}
		}else {
			Manager manager = new Manager();
			manager.setName(username);
			manager.setPassword(sb.toString());
			manager.setUserId(userId);
			if(team.equalsIgnoreCase("None")) {
				ModelAndView mv = new ModelAndView("redirect:/Index/Register");
				mv.addObject("message", "Please select a team for manager");
				return mv;
			}
			Team teams = authService.getTeam(Integer.parseInt(team));
			manager.setTeam(teams);
			boolean isSuccess = authService.saveManager(manager);
			if(!isSuccess) {
				ModelAndView mv = new ModelAndView("redirect:/Index/Register");
				mv.addObject("message", "userId already exist");
				return mv;
			}
		}
			return 	new ModelAndView("redirect:/Index/User");
		}catch(Exception e) {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("userPassword");
			return 	new ModelAndView("redirect:/Index/User");
		}
	}
}
