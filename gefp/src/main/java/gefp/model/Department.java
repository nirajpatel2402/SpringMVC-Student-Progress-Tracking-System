package gefp.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "department")
public class Department {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	String name;

	@OneToOne
    FlightPlan currentPlan;

	@OneToMany(mappedBy = "department")
    List<FlightPlan> plans;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FlightPlan getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(FlightPlan currentPlan) {
		this.currentPlan = currentPlan;
	}

	public List<FlightPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<FlightPlan> plans) {
		this.plans = plans;
	}	
    
    
	

}
