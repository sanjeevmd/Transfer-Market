package transfermarketproject.DAO;

import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.security.Key;
import transfermarketproject.Model.Manager;
import transfermarketproject.Model.Player;
import transfermarketproject.Model.Team;

@Repository
public class loginUserImplDAO implements IloginUser {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	private Session getSession(){
		  return sessionfactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	public Player isValidPlayer(String username, String password, String alternatePwd) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE userId = :userId"); 
		q.setString("userId", username);
		Player player = (Player) q.uniqueResult();
		
		 byte[] bb = new byte[player.getPassword().length()];
         for (int i=0; i<player.getPassword().length(); i++) {
             bb[i] = (byte) player.getPassword().charAt(i);
         }
         
     	byte[] bc = new byte[alternatePwd.length()];
        for (int i=0; i< alternatePwd.length(); i++) {
            bc[i] = (byte) alternatePwd.charAt(i);
        }
         
         String key = "Bar12345Bar12345";
         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
         String decrypted = "";
         String decBC= "";
         try{
        	 Cipher cipher = Cipher.getInstance("AES");
             cipher.init(Cipher.DECRYPT_MODE, aesKey);
             decrypted = new String(cipher.doFinal(bb));
             decBC = new String(cipher.doFinal(bc));            
         }catch(Exception e) {
        	 
         }
		
		if(player != null && (decrypted.equals(password) || decBC.equals(decrypted))) {
			return player;
		}else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public Manager isValidManager(String username, String password, String alternatePwd) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Manager WHERE userId = :userId"); 
		q.setString("userId", username);
		Manager manager = (Manager) q.uniqueResult();
		

		byte[] bb = new byte[manager.getPassword().length()];
        for (int i=0; i< manager.getPassword().length(); i++) {
            bb[i] = (byte) manager.getPassword().charAt(i);
        }
        
    	byte[] bc = new byte[alternatePwd.length()];
        for (int i=0; i< alternatePwd.length(); i++) {
            bc[i] = (byte) alternatePwd.charAt(i);
        }
                
        String key = "Bar12345Bar12345";
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        String decrypted = "";
        String decBC= "";
        try{
       	 	Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            decrypted = new String(cipher.doFinal(bb));  
            decBC = new String(cipher.doFinal(bc));            
        }catch(Exception e) {
       	 
        }
		
		if(manager != null && (decrypted.equals(password) || decBC.equals(decrypted))) {
			return manager;
		}else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeams(){
		List<Team> teams = sessionfactory.getCurrentSession().createCriteria(Team.class).list(); 
		return teams;
	}
	
	@SuppressWarnings("unchecked")
	public boolean savePlayer(Player player) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Player WHERE userId = :userId");
		q.setString("userId", player.getUserId());
		Player isPlayer = (Player) q.uniqueResult();
		if(isPlayer == null) {
			sessionfactory.getCurrentSession().persist(player);
			return true;
		}else {
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean saveManager(Manager manager) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Manager WHERE userId = :userId OR teamID = :teamID");
		q.setString("userId", manager.getUserId());
		q.setInteger("teamID", manager.getTeam().getId());
		Manager isManager = (Manager) q.uniqueResult();
		if(isManager == null) {
			sessionfactory.getCurrentSession().persist(manager);
			return true;
		}
		else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public Team getTeam(int teamID) {
		Query q = sessionfactory.getCurrentSession().createQuery("FROM Team WHERE id = :teamID"); 
		q.setInteger("teamID", teamID);
		Team team = (Team) q.uniqueResult();
		return team;
	}

	

}
