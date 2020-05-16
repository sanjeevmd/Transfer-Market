package transfermarketproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import transfermarketproject.DAO.IManagerImplDAO;
import transfermarketproject.DAO.IPlayer;
import transfermarketproject.DAO.IPlayerImplDAO;
import transfermarketproject.Model.Player;

@Service
@Qualifier("PlayerService")
@Transactional
public class PlayerService implements IPlayer {

	IPlayerImplDAO iplayer;
		
	@Autowired
	public void setIplayerDAO(IPlayerImplDAO iplayer) {
		this.iplayer = iplayer;
	}

	@Override
	public void applyforBid(Player player) {
		iplayer.applyforBid(player);		
	}

}
