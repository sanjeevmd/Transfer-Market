package transfermarketproject.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="player")
public class Player {
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	private int id;
	
	 @Column(name = "name")
	private String name;

	 @Column(name = "userId")
	private String userId;
		 	
	 @Column(name = "password")
	private String password;
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JoinColumn(name="teamID")
	@ManyToOne
	private Team team;


	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


	public Team getCbTeam() {
		return cbTeam;
	}

	public void setCbTeam(Team cbTeam) {
		this.cbTeam = cbTeam;
	}

	@JoinColumn(name="cbTeamId")
	@ManyToOne
	private Team cbTeam;
	
	 @Column(name = "cbAmount")
	private Float cbAmount;
	
	 @Column(name = "basePrice")
	private Float basePrice;
	
	 public Float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	@Column(name = "isAvailable")
	private Boolean isAvailable;

	 @Column(name = "bidLastDate")
	private Date bidLastDate;
	
	 @Column(name = "isAccepted")
	private Boolean isAccepted;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getCbAmount() {
		return cbAmount;
	}

	public void setCbAmount(Float cbAmount) {
		this.cbAmount = cbAmount;
	}

	public Boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Date getBidLastDate() {
		return bidLastDate;
	}

	public void setBidLastDate(Date bidLastDate) {
		this.bidLastDate = bidLastDate;
	}

	public Boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
}
