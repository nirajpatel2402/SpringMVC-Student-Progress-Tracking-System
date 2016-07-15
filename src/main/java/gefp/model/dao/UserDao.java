package gefp.model.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import gefp.model.Cell;
import gefp.model.Checkpoints;
import gefp.model.Department;
import gefp.model.FlightPlan;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.User;

public interface UserDao {

    User getUser( Integer id );
    
    List<User> getUsers();
    
    List<Department> getDepartments();
    Department getDepartment(Integer id);
    Department saveDepartment(Department department);
    
    List<Runway> getRunwaysByPlanId(Integer id);
    List<Runway> getRunways(Integer id);
    Runway saveRunway(Runway runway);
    Runway getRunway(Integer id);
    
    List<Stage> getStages(Integer id);
    Stage saveStage(Stage stage);
    List<Stage> getStagesByPlanId(Integer id);
    
    List<Checkpoints>getCheckpoints();
    Checkpoints saveCheckpoint(Checkpoints checkpoints);
    Checkpoints getCheckpointById(Integer id);
    
    List<Cell> getCellsByPlanId(Integer id);
    Cell saveCell(Cell cell);
	
    List<FlightPlan> getPlansByDepartment(Integer id);
	FlightPlan getPlans(Integer id);
	List<FlightPlan>getAllPlans();
	FlightPlan savePlan(FlightPlan plan);
	
	List<Cell> getCellByPlanId(Integer id);
	Stage getStage(Integer id);

	User saveUser(User user);

	User getUserByUsername(String userid);

	User getUserByCin(String userid);
 
	User getUserByEmail(String userid);  
	List<User> getUserListByFnameLname(String str1, String str2);
	List<User> getUserListByFname(String str1);
	List<User> getUserListByLname(String str1);

	List<User> searchUsersByPrefix(String term, int i);

	Stage getStageByPosition(Integer toPosition, Integer cplan);

	List<Stage> getAllStages();
	List<Runway> getAllRunways();
	Checkpoints getCheckpointByPosition(Integer toPosition, Integer currentCell);

	Runway getRunwayByPosition(Integer rposition, Integer currentPlan);

	List<Checkpoints> getCheckpointByPlan(Integer currentPlan);     

}