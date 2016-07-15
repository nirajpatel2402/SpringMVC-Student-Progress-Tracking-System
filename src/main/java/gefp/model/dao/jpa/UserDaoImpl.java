package gefp.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gefp.model.Cell;
import gefp.model.Checkpoints;
import gefp.model.Department;
import gefp.model.FlightPlan;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.User;
import gefp.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
	@PostAuthorize("hasRole('advisor') or principal.username == returnObject.username")
    public User getUser( Integer id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from Department order by id", Department.class ).getResultList();
	}

	@Override
	public List<Runway> getRunways(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select r from Runway r where r.plan.id = :id order by r.rposition", Runway.class ).setParameter("id", id)
	            .getResultList();
	}

	@Override
	public List<Stage> getStages(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select s from Stage s where s.plan.id = :id order by s.position", Stage.class ).setParameter("id", id)
	            .getResultList();
	}

	@Override
	public List<Checkpoints> getCheckpoints() {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from Checkpoints order by cposition", Checkpoints.class )
	            .getResultList();
	}

	@Override
	public List<Cell> getCellsByPlanId(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select c from Cell c where c.plan.id = :id order by id", Cell.class ).setParameter("id", id)
	            .getResultList();
	}

	
	@Transactional
	@Override
	public Stage saveStage(Stage stage) {
		// TODO Auto-generated method stub
		return entityManager.merge( stage );
	}

	@Override
	@PostAuthorize("hasRole('administrator') or principal.plan == returnObject")
	public FlightPlan getPlans(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find( FlightPlan.class, id );
	}
	
	@Override
	public Stage getStage(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find( Stage.class, id );
	}
	
	@Override
	public Runway getRunway(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find( Runway.class, id );
	}

	@Transactional
	@Override
	public Runway saveRunway(Runway runway) {
		// TODO Auto-generated method stub
		return entityManager.merge( runway );
	}

	@Transactional
	@Override
	public Checkpoints saveCheckpoint(Checkpoints checkpoints) {
		// TODO Auto-generated method stub
		return entityManager.merge( checkpoints );
	}

	@Override
	public List<Runway> getRunwaysByPlanId(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select r from Runway r where r.plan.id = :id order by r.rposition", Runway.class ).setParameter("id", id).getResultList();
	}

	@Transactional
	@Override
	public Cell saveCell(Cell cell) {
		// TODO Auto-generated method stub
		return entityManager.merge( cell );
	}

	@Override
	public List<Stage> getStagesByPlanId(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select s from Stage s where s.plan.id = :id order by id", Stage.class ).setParameter("id", id).getResultList();
	}

	@Override
	public List<Cell> getCellByPlanId(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select c from Cell c where c.plan.id = :id order by id", Cell.class ).setParameter("id", id).getResultList();
	}

	@Override
	public Checkpoints getCheckpointById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find( Checkpoints.class, id );
	}

	@Override
	public Department getDepartment(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find( Department.class, id );
	}

	@Transactional
	@Override
	public FlightPlan savePlan(FlightPlan plan) {
		// TODO Auto-generated method stub
		return entityManager.merge( plan );
		}

	@Override
	public List<FlightPlan> getAllPlans() {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from FlightPlan order by id", FlightPlan.class )
	            .getResultList();
	}

	@Override
	public List<FlightPlan> getPlansByDepartment(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select p from FlightPlan p where p.department.id = :id order by id", FlightPlan.class ).setParameter("id", id)
	            .getResultList();
	}

	@Transactional
	@Override
	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return entityManager.merge(department);
	}

	@Transactional 
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub 
		return entityManager.merge(user);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		String query = "from User user where username = :username";

	        List<User> users = entityManager.createQuery( query, User.class )
	            .setParameter( "username", username.toLowerCase() )
	            .getResultList();
	        return users.size() == 0 ? null : users.get( 0 );
	}

	@Override
	public User getUserByCin(String cin) {
		// TODO Auto-generated method stub
		List<User> users = entityManager.createQuery(
	            "from User where cin = :cin", User.class )
	            .setParameter( "cin", cin )
	            .getResultList();
	        return users.size() == 0 ? null : users.get( 0 );
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		 String query = "from User where email = :email";

	        List<User> users = entityManager.createQuery( query, User.class )
	            .setParameter( "email", email.toLowerCase() )
	            .getResultList();
	        return users.size() == 0 ? null : users.get( 0 );
	}

	@Override
	public List<User> getUserListByFnameLname(String str1, String str2) {
		// TODO Auto-generated method stub
		String query1 = "from User where first_name = :str1 and last_name = :str2";
		String query2 = "from User where first_name = :str2 and last_name = :str1";
		
        List<User> userList1 = entityManager.createQuery( query1, User.class )
            .setParameter( "str1", str1.toLowerCase() ).setParameter("str2", str2.toLowerCase() )
            .getResultList();
        
        List<User> userList2 = entityManager.createQuery( query2, User.class )
        		.setParameter( "str1", str1.toLowerCase() ).setParameter("str2", str2.toLowerCase() )
                .getResultList();
        userList1.addAll(userList2); 
          
        return userList1.size() == 0 ? null : userList1;
	}

	@Override
	public List<User> getUserListByFname(String fname) {
		// TODO Auto-generated method stub
		 String query = "from User where first_name = :fname";

	        List<User> users = entityManager.createQuery( query, User.class )
	            .setParameter( "fname", fname.toLowerCase() )
	            .getResultList();
	        return users.size() == 0 ? null : users;
	}

	@Override
	public List<User> getUserListByLname(String lname) {
		// TODO Auto-generated method stub
		 String query = "from User where last_name = :lname";

	        List<User> users = entityManager.createQuery( query, User.class )
	            .setParameter( "lname", lname.toLowerCase() )
	            .getResultList();
	        return users.size() == 0 ? null : users;
	}

	@Override
	public List<User> searchUsersByPrefix(String term, int maxResults) {
		// TODO Auto-generated method stub
		 term = term.toLowerCase();
	        String query = "from User where cin like :term || '%' "
	            + "or lower(username) like :term || '%' "
	            + "or lower(first_name) like :term || '%' "
	            + "or lower(last_name) like :term || '%' "
	            + "or lower(first_name || ' ' || last_name) like :term || '%' "
	            + "or lower(email) like :term || '%' "
	            + "order by first_name asc";

	        return entityManager.createQuery( query, User.class )
	            .setParameter( "term", term )
	            .setMaxResults( maxResults )
	            .getResultList();
	}

	@Override
	public Stage getStageByPosition(Integer position, Integer cplan) {
		// TODO Auto-generated method stub
//		String query = "from User user where position = :position";
//
//        List<Stage> stage = entityManager.createQuery( query, Stage.class )
//            .setParameter( "position", String.valueOf(position) )
//            .getResultList();
//        return stage.size() == 0 ? null : stage.get( 0 );
		  return entityManager.createQuery( "select s from Stage s where s.position = :position and s.plan.id = :cplan", Stage.class ).setParameter("position", position).setParameter("cplan", cplan).getSingleResult();
	}

	@Override
	public List<Stage> getAllStages() {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from Stage order by position", Stage.class )
	            .getResultList();
	}

	@Override
	public Checkpoints getCheckpointByPosition(Integer cposition, Integer currentCell) {
		// TODO Auto-generated method stub
		  return entityManager.createQuery( "select c from Checkpoints c where c.cposition = :cposition and c.cell.id = :ccell", Checkpoints.class ).setParameter("cposition", cposition).setParameter("ccell", currentCell).getSingleResult();
	}

	@Override
	public Runway getRunwayByPosition(Integer rposition, Integer cplan) {
		// TODO Auto-generated method stub
		  return entityManager.createQuery( "select r from Runway r where r.rposition = :rposition and r.plan.id = :cplan", Runway.class ).setParameter("rposition", rposition).setParameter("cplan", cplan).getSingleResult();
	}

	@Override
	public List<Runway> getAllRunways() {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "from Runway order by rposition", Runway.class )
	            .getResultList(); 
	}

	@Override
	public List<Checkpoints> getCheckpointByPlan(Integer currentPlan) {
		// TODO Auto-generated method stub
		return entityManager.createQuery( "select c from Checkpoints c where c.cell.plan.id = :id order by c.cposition", Checkpoints.class ).setParameter("id", currentPlan)
	            .getResultList();
	}



}