package transfermarketproject.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;
import transfermarketproject.Model.Watch;


@Repository
public class IManagerImplDAO implements IManager {
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getSession(){
		  return sessionfactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers(int teamID){
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE teamID = :teamID AND isAvailable = 1 AND isAccepted = 1"); 
		q.setInteger("teamID", teamID);
		List<Player> players = (List<Player>) q.list();
		if(players.size() > 0) {
			return players;
		}
		return null;
	};
	
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayers(Manager manager,String search){
		Query q;
		if(search == null) {
		 q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE teamID != :teamID"); 
		 q.setInteger("teamID", manager.getTeam().getId());
		}else {
		 q = sessionfactory.getCurrentSession().createQuery("FROM Player Where name like concat(:search,'%') AND teamID != :teamID"); 
		 q.setString("search",  search);
		 q.setInteger("teamID", manager.getTeam().getId());
		}
		List<Player> players = (List<Player>) q.list();
		if(players.size() > 0) {
			return players;
		}
		return null;
	};
	
	@SuppressWarnings("unchecked")
	public List<Player> getOpenPlayers(int teamID){
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE (isAvailable = 0 AND teamID != :teamID) or (isAvailable = 0 AND teamID IS NULL)"); 
		q.setInteger("teamID", teamID);
		List<Player> players = (List<Player>) q.list();
		if(players.size() > 0) {
			return players;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayersOnBid(int teamID){
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE (isAvailable = 0 AND teamID = :teamID) "); 
		q.setInteger("teamID", teamID);
		List<Player> players = (List<Player>) q.list();
		if(players.size() > 0) {
			return players;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void makePlayerAvailable(int playerID, Date date, Float bidAmount) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE id = :ID AND isAvailable = 1"); 
		q.setInteger("ID", playerID);
		Player player = (Player) q.uniqueResult();
		player.setAvailable(false);
		player.setBidLastDate(date);
		player.setBasePrice(bidAmount);
		player.setAccepted(false);
		Float f = player.getBasePrice();
		player.setCbAmount(f);
		sessionfactory.getCurrentSession().update(player);
	}
	
	@SuppressWarnings("unchecked")
	public void addWatch(Manager manager, int player) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Watch WHERE managerId = :managerId AND playerId = :playerId"); 
		q.setInteger("managerId", manager.getId());
		q.setInteger("playerId", player);
		Watch watcher = (Watch) q.uniqueResult();
		if(watcher == null) {
			Query qa = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE id = :playerId"); 
			qa.setInteger("playerId", player);
			Player players = (Player) qa.uniqueResult();
			Watch watch = new Watch();
			watch.setManager(manager);
			watch.setPlayer(players);
			sessionfactory.getCurrentSession().persist(watch);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Watch> viewWatch(int managerId){
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Watch WHERE managerId = :managerId"); 
		q.setInteger("managerId", managerId);
		List<Watch> watch = (List<Watch>) q.list();
		return watch;
	}

	@SuppressWarnings("unchecked")
	public void deleteWatch(int id) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Watch WHERE id = :id");
		q.setInteger("id", id);
		Watch watch = (Watch) q.uniqueResult();
		sessionfactory.getCurrentSession().delete(watch);
	}

	@SuppressWarnings("unchecked")
	public void updateBid(int playerID, Float bidAmount, Manager manager) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE id = :playerID");
		q.setInteger("playerID", playerID);
		Player player = (Player) q.uniqueResult();
		Date date = new Date();
		if((player.getCbAmount() < bidAmount) && (player.getBidLastDate().compareTo(date) >= 0)) {
			player.setCbTeam(manager.getTeam());
			player.setCbAmount(bidAmount);
			sessionfactory.getCurrentSession().update(player);
		}
	}

	@Override
	public Player getPlayer(int playerId) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE id = :playerID");
		q.setInteger("playerID", playerId);
		Player player = (Player) q.uniqueResult();
		return player;
	}

}
