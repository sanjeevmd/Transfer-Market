package transfermarketproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import transfermarketproject.DAO.IloginUser;
import transfermarketproject.DAO.loginUserImplDAO;
import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;


@Service
@Transactional
public class AuthService implements IloginUser {

	loginUserImplDAO loginDAO;
	
	@Autowired
	public void setLoginDAO(loginUserImplDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public Player isValidPlayer(String username, String password, String altPwd){
		return loginDAO.isValidPlayer(username, password, altPwd);
	}
	
	public Manager isValidManager(String username, String password, String altPwd){
		return loginDAO.isValidManager(username, password, altPwd);
	}
	
	public List<Team> getTeams(){
		return loginDAO.getTeams();
	}
	
	public boolean savePlayer(Player player) {
		return loginDAO.savePlayer(player);
	}
	public boolean saveManager(Manager manager) {
		return loginDAO.saveManager(manager);
	}
	public Team getTeam(int teamID) {
		return loginDAO.getTeam(teamID);
	}
	
}
