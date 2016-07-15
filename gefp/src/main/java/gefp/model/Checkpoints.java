package gefp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkpoints")
public class Checkpoints {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer cposition;
	private String description;
	private boolean ischecked=false;
	
	public boolean isIschecked() {
		return ischecked;
	}
	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}
	@ManyToMany(mappedBy = "checkpoints",cascade=CascadeType.ALL)
	List<User> user;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	Cell cell;
	
	public Checkpoints(){
		
	}
	
	
	public Integer getCposition() {
		return cposition;
	}
	public void setCposition(Integer cposition) {
		this.cposition = cposition;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public Cell getCell() {
		return cell;
	}
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	
}
