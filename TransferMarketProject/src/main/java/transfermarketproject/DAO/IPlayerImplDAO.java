package transfermarketproject.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import transfermarketproject.Model.Player;

@Repository
public class IPlayerImplDAO implements IPlayer {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getSession(){
		  return sessionfactory.getCurrentSession();
	}

	public void applyforBid(Player player) {
		sessionfactory.getCurrentSession().update(player);
	}
}
