package transfermarketproject.DAO;

import java.util.List;

import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;


public interface IloginUser {
	
	public Player isValidPlayer(String username, String password, String alternatePwd);

	public Manager isValidManager(String username, String password, String alternatePwd);
	
	public List<Team> getTeams();
	
	public boolean savePlayer(Player player);
	
	public boolean saveManager(Manager manager);

	public Team getTeam(int teamID);

}
