package gefp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stage")
public class Stage {
	
	@Id
	@GeneratedValue
	Integer id;
	
	String sname;
	
	@ManyToOne
	FlightPlan plan;
	
	@GeneratedValue
	Integer position;
	
	
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Stage(){
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public FlightPlan getPlan() {
		return plan;
	}

	public void setPlan(FlightPlan plan) {
		this.plan = plan;
	}
	
	

}
