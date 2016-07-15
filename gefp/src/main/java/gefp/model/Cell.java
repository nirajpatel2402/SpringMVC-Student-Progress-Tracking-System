package gefp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cell")
public class Cell {

	@Id
	@GeneratedValue
	Integer id;

	@ManyToOne
    FlightPlan plan;

	@ManyToOne
    Runway runway;

	@ManyToOne(cascade=CascadeType.PERSIST) 
    Stage stage;

	@OneToMany(mappedBy = "cell")
//	@JoinTable(name="cell_checkpoints",
//	joinColumns= @JoinColumn(name="cell_id"),
//    inverseJoinColumns=@JoinColumn(name="checkpoints_id"))
	
    List<Checkpoints> checkpoints;

	
	public Cell(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FlightPlan getPlan() {
		return plan;
	}

	public void setPlan(FlightPlan plan) {
		this.plan = plan;
	}

	public Runway getRunway() {
		return runway;
	}

	public void setRunway(Runway runway) {
		this.runway = runway;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Checkpoints> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoints> checkpoints) {
		this.checkpoints = checkpoints;
	}
    
    
}
