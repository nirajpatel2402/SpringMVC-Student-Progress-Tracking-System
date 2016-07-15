package gefp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="runway")
public class Runway {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer rposition;

	private String description;
	
	@ManyToOne
	FlightPlan plan;
	
	public Runway(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRposition() {
		return rposition;
	}

	public void setRposition(Integer rposition) {
		this.rposition = rposition;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public FlightPlan getPlan() {
		return plan;
	}
	public void setPlan(FlightPlan plan) {
		this.plan = plan;
	}

	
}
