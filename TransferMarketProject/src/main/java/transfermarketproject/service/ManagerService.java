package transfermarketproject.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import transfermarketproject.DAO.IManager;
import transfermarketproject.DAO.IManagerImplDAO;
import transfermarketproject.DAO.loginUserImplDAO;
import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;
import transfermarketproject.Model.Watch;


@Service
@Qualifier("ManagerService")
@Transactional
public class ManagerService implements IManager {

	IManagerImplDAO IManager;
	
	@Autowired
	public void setIManagerDAO(IManagerImplDAO IManager) {
		this.IManager = IManager;
	}

	public List<Player> getAllPlayers(int teamID) {
		return IManager.getAllPlayers(teamID);
	}
	
	public List<Player> getAllPlayers(Manager manager,String search) {
		return IManager.getAllPlayers(manager,search);
	}

	public void makePlayerAvailable(int playerID, Date date, Float bidAmount) {
		IManager.makePlayerAvailable(playerID,date, bidAmount);
	}

	public List<Player> getOpenPlayers(int teamID) {
		return IManager.getOpenPlayers(teamID);
	}
	public List<Player> getPlayersOnBid(int teamID) {
		return IManager.getPlayersOnBid(teamID);
	}

	public void addWatch(Manager managerId, int playerId) {
		 IManager.addWatch(managerId, playerId);	
	}
	
	public List<Watch> viewWatch(int managerId){
		return IManager.viewWatch(managerId);
	}

	public void deleteWatch(int watchId) {
		IManager.deleteWatch(watchId);
	}

	public void updateBid(int playerID, Float bidAmount, Manager manager) {
		IManager.updateBid(playerID, bidAmount, manager);
	}

	@Override
	public Player getPlayer(int playerId) {
		return IManager.getPlayer(playerId);
	}

}
