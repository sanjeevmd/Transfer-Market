package transfermarketproject.DAO;

import java.util.Date;
import java.util.List;

import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;
import transfermarketproject.Model.Watch;

public interface IManager {
	public List<Player> getAllPlayers(int teamID);
	
	public List<Player> getOpenPlayers(int teamID);
	
	public List<Player> getPlayersOnBid(int teamID);

	public void makePlayerAvailable(int playerID,Date date, Float bidAmount);
	
	public List<Player> getAllPlayers(Manager manager,String search);
	
	public void addWatch(Manager managerId, int playerId);
	
	public List<Watch> viewWatch(int managerId);
	
	public void deleteWatch(int watchId);
	
	public void updateBid(int playerID,Float bidAmount, Manager manager);
	
	public Player getPlayer(int playerId);
}