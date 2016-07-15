package gefp.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import gefp.security.SecurityUtils;
import gefp.web.validator.UserValidator;
import gefp.model.Cell;
import gefp.model.Checkpoints;
import gefp.model.Department;
import gefp.model.FlightPlan;
import gefp.model.Roles;
import gefp.model.Runway;
import gefp.model.Stage;
import gefp.model.User;
import gefp.model.dao.UserDao;

@Controller
@SessionAttributes({ "stage", "plan", "user", "checkpoint", "loggeduser",
		"department", "current_department", "departmentname", "departmentid",
		"planid" })
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	UserValidator userValidator;

	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String users(ModelMap models) {
		models.put("user", new User());
		return "login";
	}

	@RequestMapping(value = "/landing.html")
	public String users(HttpSession session,Principal principal) {
//		User user = userDao.getUserByUsername(name);
		
//		String name = SecurityUtils.getUser();
//		System.out.println("name:"+name);
//		User user = userDao.getUserByUsername(name);
		User user = SecurityUtils.getUser();
		System.out.println("username:"+user.getUsername());
		System.out.println("full name:"+user.getFirst_name()+user.getLast_name());
		System.out.println("cin:"+user.getCin());
		System.out.println("role:"+user.getRoles());
		
		
		List<Roles> roles = new ArrayList<Roles>();
				for (Roles p : user.getRoles()) {  
					roles.add(p);
				}
				for (int j = 0; j < roles.size(); j++) {
					System.out.println("rolename :"
							+ roles.get(j).getRolename());
					if (roles.get(j).getRolename().equals("student")) {
						System.out.println("user is student :" + user);
						session.setAttribute("loggeduser", user);
						return "redirect:studenthome.html";
					} else {
						if (roles.get(j).getRolename().equals("administrator")) {
							System.out.println("user is administrator");
							session.setAttribute("loggeduser", user);
							return "redirect:adminhome.html";
						} else {
							if (roles.get(j).getRolename().equals("advisor")) {
								System.out.println("user is advisor");
								session.setAttribute("loggeduser", user);
								return "redirect:advisorhome.html";
							}
						}
					}
				}
				roles.clear();

		return "redirect:login.html";
	}

	
//	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
//	public String users(@ModelAttribute User user, BindingResult result,
//			HttpSession session) {
//		List<User> users = userDao.getUsers();
//		for (int i = 0; i < users.size(); i++) {
//			if (users.get(i).getUsername().equals(user.getUsername())
//					&& users.get(i).getPassword().equals(user.getPassword())) {
//				List<Roles> roles = new ArrayList<Roles>();
//				for (Roles p : users.get(i).getRoles()) {
//					roles.add(p);
//				}
//				for (int j = 0; j < roles.size(); j++) {
//					System.out.println("rolename :"
//							+ roles.get(j).getRolename());
//					if (roles.get(j).getRolename().equals("student")) {
//						System.out.println("user is student :" + users.get(i));
//						session.setAttribute("loggeduser", users.get(i));
//						return "redirect:studenthome.html";
//					} else {
//						if (roles.get(j).getRolename().equals("administrator")) {
//							System.out.println("user is administrator");
//							session.setAttribute("loggeduser", users.get(i));
//							return "redirect:adminhome.html";
//						} else {
//							if (roles.get(j).getRolename().equals("advisor")) {
//								System.out.println("user is advisor");
//								session.setAttribute("loggeduser", users.get(i));
//								return "redirect:advisorhome.html";
//							}
//						}
//					}
//				}
//				roles.clear();
//			}
//		}
//
//		return "redirect:login.html";
//	}

	
	@RequestMapping(value = "/advisorhome.html", method = RequestMethod.GET)
	public String advisorhome(ModelMap models, HttpSession session) {
//		User user = (User) session.getAttribute("loggeduser");
		User user = SecurityUtils.getUser();
		models.put("user", userDao.getUser(user.getId()));
		return "advisorhome";
	}

	@RequestMapping(value = "/advisorhome.html", method = RequestMethod.POST)
	public String advisorhome(@RequestParam String userid, ModelMap models,
			HttpSession session) {
		// User user = (User) session.getAttribute("loggeduser");
		User userSearched = null;

		List<User> usersSearched = null;
		String error = "";

		 String splitter[] = userid.split("\\s+");
		 for(int i=0; i<splitter.length; i++){
			 System.out.println("s1 :"+splitter[i]);
		 } 
		 if(splitter.length>1){
			 usersSearched = userDao.getUserListByFnameLname(splitter[0],splitter[1]);
		 for(int i=0; i<usersSearched.size(); i++){
			 System.out.println("user full name :"+usersSearched.get(i).getFirst_name()+" "+usersSearched.get(i).getLast_name());
		 	}
		 }else{
			 if (userDao.getUserByUsername(userid) != null || userDao.getUserByCin(userid) != null || userDao.getUserByEmail(userid) != null || userDao.getUserListByLname(userid) != null || userDao.getUserListByFname(userid) != null) {
					if (userDao.getUserByUsername(userid) == null) {
						if (userDao.getUserByCin(userid) == null) {
							if (userDao.getUserByEmail(userid) == null) {
								if (userDao.getUserListByFname(userid) == null) {
									if (userDao.getUserListByLname(userid) == null) {
										
									} else {
										System.out.println("lname found");
										usersSearched = userDao.getUserListByLname(userid);
										for (int i = 0; i < usersSearched.size(); i++) {
											System.out.println("user"+ i+ ":"+ usersSearched.get(i).getFirst_name()+ " "+ usersSearched.get(i).getLast_name());
										}
									}
								} else {
									System.out.println("fname found");
									usersSearched = userDao.getUserListByFname(userid);
									for (int i = 0; i < usersSearched.size(); i++) {
										System.out.println("user" + i + ":"+ usersSearched.get(i).getFirst_name()+ " "+ usersSearched.get(i).getLast_name());
									}
								}
							} else {
								System.out.println("email found");
								userSearched = userDao.getUserByEmail(userid);
							}
						} else {
							System.out.println("cin found");
							userSearched = userDao.getUserByCin(userid);
						}
					} else {
						System.out.println("username found");
						userSearched = userDao.getUserByUsername(userid);
					}
				}else {
					System.out.println("nothing found!!");
					error = "No result found!!";
					models.put("error", error);
				}
		 }
		models.put("usersSearched", usersSearched);
		models.put("userSearched", userSearched);
		return "advisorhome";
	}

	@RequestMapping(value = "/advisorview.html")
	public String advisorview(@RequestParam Integer searchuser,
			ModelMap models, HttpSession session) {
		User searchUser = userDao.getUser(searchuser);
		System.out.println("plan id :" + searchUser.getPlan().getId());

		models.put("runway", userDao.getRunways(searchUser.getPlan().getId()));
		models.put("stage", userDao.getStages(searchUser.getPlan().getId()));

		List<Checkpoints> allCheckpoint = userDao.getCheckpoints();
		List<Checkpoints> checkedCheckpoint = searchUser.getCheckpoints();
		for (int i = 0; i < allCheckpoint.size(); i++) {

			for (int j = 0; j < checkedCheckpoint.size(); j++) {

				if (allCheckpoint.get(i).getId() == checkedCheckpoint.get(j)
						.getId()) {
					// System.out.println("value changed :"+allCheckpoint.get(i).getId());
					allCheckpoint.get(i).setIschecked(true);
					// System.out.println("allCheckpoint id :"+allCheckpoint.get(i).getId());
				} else {
					// System.out.println("allunCheckpoint id :"+allCheckpoint.get(i).getId());
				}
			}
		}
		models.put("searchUser", searchUser);
		models.put("checkpoints", allCheckpoint);
		models.put("cell",
				userDao.getCellsByPlanId(searchUser.getPlan().getId()));
		models.put("planid", searchUser.getPlan().getId());
		return "advisorview";
	}

	@RequestMapping(value = "/checkpoint.html", method = RequestMethod.POST)
	public String checkpoint(@RequestParam Integer checkpointId,
			@RequestParam(required = false) Integer searchuser,
			HttpSession session) {
		System.out.println("Checkpoint checked :" + checkpointId);
		System.out.println("searchuser :" + searchuser);
		User user = null;
		if (searchuser == null) {
//			user = (User) session.getAttribute("loggeduser");
			user = SecurityUtils.getUser();
		} else {
			user = userDao.getUser(searchuser);
		}

		System.out.println("user checkpoint size :"
				+ user.getCheckpoints().size());
		Checkpoints cp = userDao.getCheckpointById(checkpointId);

		List<Checkpoints> cpoint = userDao.getUser(user.getId())
				.getCheckpoints();
		System.out.println("userdao checkpoint size :" + cpoint.size());
		cpoint.add(cp);
		// userDao.getUser(user.getId()).getCheckpoints().add(cp);
		user.setCheckpoints(cpoint);
		// user.getCheckpoints().add(cp);
		userDao.saveUser(user);
		return null;
	}

	@RequestMapping(value = "/checkpointremove.html", method = RequestMethod.POST)
	public String checkpointremove(@RequestParam Integer checkpointId,
			@RequestParam(required = false) Integer searchuser,
			HttpSession session) {
		System.out.println("Checkpoint removed :" + checkpointId);
		System.out.println("searchuser :" + searchuser);
		User user = null;
		if (searchuser == null) {
//			user = (User) session.getAttribute("loggeduser");
			user = SecurityUtils.getUser();
			System.out.println("searchuser null");
		} else {
			user = userDao.getUser(searchuser);
			System.out.println("searchuser exist");
		}
		// User user = (User) session.getAttribute("loggeduser");
		Checkpoints cp = userDao.getCheckpointById(checkpointId);

		System.out.println("cp id to be removed :" + cp.getId());
		System.out.println("user checkpoint size :"
				+ user.getCheckpoints().size());

		List<Checkpoints> cpoint = userDao.getUser(user.getId())
				.getCheckpoints();
		System.out.println("userdao checkpoint size :" + cpoint.size());
		cpoint.remove(cp);
		user.setCheckpoints(cpoint);
		userDao.saveUser(user);
		return null;
	}

	@RequestMapping(value = "/adminhome.html", method = RequestMethod.GET)
	public String adminhome(@RequestParam(required = false) String action,
			@RequestParam(required = false) Integer departmentid,
			@RequestParam(required = false) Integer planid, ModelMap models,
			HttpSession session) {

		if (action != null) {
			Department department = userDao.getDepartment(departmentid);
			department.setCurrentPlan(userDao.getPlans(planid));
			userDao.saveDepartment(department);
		}

		User user = SecurityUtils.getUser();
//		User user = (User) session.getAttribute("loggeduser");
		models.put("user", user);
		models.put("department", userDao.getDepartments());
		return "adminhome";
	}

	@RequestMapping(value = "/account.html", method = RequestMethod.GET)
	public String account(ModelMap models, HttpSession session) {

//		User user = (User) session.getAttribute("loggeduser");
		User user = SecurityUtils.getUser();
		// models.put("department", userDao.getDepartments());
		session.setAttribute("department", userDao.getDepartments());
		// models.put( "current_department", user.getMajor() );
		session.setAttribute("current_department", user.getMajor());
		models.put("user", user);
		return "account";
	}

	@RequestMapping(value = "/account.html", method = RequestMethod.POST)
	public String account(@ModelAttribute User user,
			BindingResult bindingResult,
			@RequestParam(required = false) Integer department,
			ModelMap models, HttpSession session) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors())
			return "account";

		user.setMajor(userDao.getDepartment(department));  
		
		if (user.getPassword().equals("")) {
			user.setPassword(userDao.getUser(user.getId()).getPassword());
		}
		System.out.println("pass :" + user.getPassword().toString());
		if (user.getMajor() != userDao.getUser(user.getId()).getMajor()) {
			System.out.println("major changed");
			user.getCheckpoints().clear();
		} else {
			System.out.println("major not changed");
		}
		user.setPlan(user.getMajor().getCurrentPlan());

		// user.getCheckpoints().clear();
		userDao.saveUser(user);
		session.removeAttribute("current_department"); 
		session.setAttribute("current_department", user.getMajor());
		return "redirect:studenthome.html";
	}

	@RequestMapping(value = "/studenthome.html")
	public String studenthome(ModelMap models, HttpSession session) {
//		User user = (User) session.getAttribute("loggeduser");
		User user = SecurityUtils.getUser();
		// System.out.println("plan id :"+user.getPlan().getId());

		models.put("runway", userDao.getRunways(user.getPlan().getId()));
		models.put("stage", userDao.getStages(user.getPlan().getId()));

		List<Checkpoints> allCheckpoint = userDao.getCheckpoints();
		List<Checkpoints> checkedCheckpoint = user.getCheckpoints();
		for (int i = 0; i < allCheckpoint.size(); i++) {

			for (int j = 0; j < checkedCheckpoint.size(); j++) {

				if (allCheckpoint.get(i).getId() == checkedCheckpoint.get(j)
						.getId()) {
					// System.out.println("value changed :"+allCheckpoint.get(i).getId());
					allCheckpoint.get(i).setIschecked(true);
					// System.out.println("allCheckpoint id :"+allCheckpoint.get(i).getId());
				} else {
					// System.out.println("allunCheckpoint id :"+allCheckpoint.get(i).getId());
				}
			}
		}

		models.put("checkpoints", allCheckpoint);
		models.put("cell", userDao.getCellsByPlanId(user.getPlan().getId()));
		models.put("planid", user.getPlan().getId());
		return "studenthome";
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.removeAttribute("stage");
		session.removeAttribute("plan");
		session.removeAttribute("user");
		session.removeAttribute("checkpoint");
		session.removeAttribute("loggeduser");
		session.removeAttribute("current_department");
		session.removeAttribute("planid");
		session.removeAttribute("department");
		session.removeAttribute("departmentid");
		session.removeAttribute("create_plan_department");
		session.removeAttribute("departmentname");
		session.invalidate();
		return "redirect:login.html";
	}

	@RequestMapping(value = "/adminview.html", method = RequestMethod.GET)
	public String adminview(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		models.put("runway", userDao.getRunways(id));
		models.put("stage", userDao.getStages(id));
		models.put("checkpoints", userDao.getCheckpoints());
		models.put("cell", userDao.getCellsByPlanId(id));
		models.put("planid", id);
		session.setAttribute("planid", id);
		return "adminview";
	}

	@RequestMapping(value = "/newstage.html", method = RequestMethod.GET)
	public String addstage(ModelMap models) {
		models.put("stage", new Stage());
		return "newstage";
	}

	@RequestMapping(value = "/newstage.html", method = RequestMethod.POST)
	public String addstage(@ModelAttribute Stage stage, HttpSession session) {
		Integer id = (Integer) session.getAttribute("planid");
		FlightPlan plan = userDao.getPlans(id);
		stage.setPlan(plan);
		Integer maxIndex;
//		for(int i=0; i<userDao.getAllStages().size(); i++){
			maxIndex = userDao.getAllStages().get(userDao.getAllStages().size()-1).getPosition();
			stage.setPosition(maxIndex+1);
//		}
		Cell cell = new Cell();
		userDao.saveStage(stage);

		List<Stage> stageList = userDao.getStagesByPlanId(plan.getId());
		Stage lastStage = new Stage();
		lastStage = stageList.get(stageList.size() - 1);

		List<Runway> runway = userDao.getRunwaysByPlanId(plan.getId());
		cell.setStage(lastStage);

		for (int i = 0; i < runway.size(); i++) {
			cell.setPlan(plan);
			cell.setRunway(runway.get(i));
			userDao.saveCell(cell);
		}
		return "redirect:adminview.html?id=" + id;
	}

	@RequestMapping(value = "/newrunway.html", method = RequestMethod.GET)
	public String addrunway(ModelMap models) {
		models.put("runway", new Runway());
		return "newrunway";
	}

	@RequestMapping(value = "/newrunway.html", method = RequestMethod.POST)
	public String addstage(@ModelAttribute Runway runway, HttpSession session) {
		Integer id = (Integer) session.getAttribute("planid");
		FlightPlan plan = userDao.getPlans(id);
		runway.setPlan(plan);
		Integer maxIndex;
		maxIndex = userDao.getAllRunways().get(userDao.getAllRunways().size()-1).getRposition();

//		maxIndex = userDao.getCheckpoints().get(userDao.getCheckpoints().size()-1).getCposition();
		runway.setRposition(maxIndex+1); 
		userDao.saveRunway(runway);

		Cell cell = new Cell();
		List<Stage> stage = userDao.getStagesByPlanId(plan.getId());
		List<Runway> runwayList = userDao.getRunwaysByPlanId(plan.getId());
		Runway lastRunway = new Runway();
		lastRunway = runwayList.get(runwayList.size() - 1);
		cell.setRunway(lastRunway);

		for (int i = 0; i < stage.size(); i++) {
			cell.setPlan(plan);
			cell.setStage(stage.get(i));
			userDao.saveCell(cell);
		}
		return "redirect:adminview.html?id=" + id;
	}

	@RequestMapping(value = "/newcheckpoint.html", method = RequestMethod.GET)
	public String addcheckpoint(ModelMap models, HttpSession session) {
		Integer id = (Integer) session.getAttribute("planid");
		models.put("runway", userDao.getRunways(id));
		models.put("stage", userDao.getStages(id));
		models.put("checkpoints", new Checkpoints());
		return "newcheckpoint";
	}

	@RequestMapping(value = "/newcheckpoint.html", method = RequestMethod.POST)
	public String addcheckpoint(@RequestParam String description,
			@RequestParam Integer stage, @RequestParam Integer runway,
			@ModelAttribute Checkpoints checkpoints, HttpSession session) {
		Checkpoints cp = new Checkpoints();
		Integer maxIndex;
		maxIndex = userDao.getCheckpoints().get(userDao.getCheckpoints().size()-1).getCposition();
		cp.setCposition(maxIndex+1);
		cp.setIschecked(false);
		Integer id = (Integer) session.getAttribute("planid");
		List<Cell> cell = userDao.getCellByPlanId(id);
		for (int i = 0; i < cell.size(); i++) {

			if (cell.get(i).getStage().getId().equals(stage)
					&& cell.get(i).getRunway().getId().equals(runway)) {
				cp.setCell(cell.get(i));
				cp.setDescription(description);
			}
		}
		userDao.saveCheckpoint(cp);
		return "redirect:adminview.html?id=" + id;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public String edit(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		models.put("runway", userDao.getRunways(plan_id));
		models.put("stage", userDao.getStages(plan_id));

		Checkpoints checkpoint = userDao.getCheckpointById(id);
		Cell cell = checkpoint.getCell();
		models.put("runway_id", cell.getRunway());
		models.put("stage_id", cell.getStage());
		models.put("checkpoints", checkpoint);
		return "edit";
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public String edit(@RequestParam String description,@RequestParam String edit,
			@RequestParam Integer stage, @RequestParam Integer runway,
			@ModelAttribute Checkpoints checkpoints, HttpSession session,
			SessionStatus sessionStatus) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		if(edit.equals("Save")){
			
			FlightPlan plan = userDao.getPlans(plan_id);
			List<Cell> cells = userDao.getCellByPlanId(plan_id);
			
			System.out.println("new checkpoints.getCposition() :"+userDao.getCheckpointById(checkpoints.getId()).getCposition()); 
			checkpoints.setCposition(userDao.getCheckpointById(checkpoints.getId()).getCposition());  

			Cell cell = null;
			for (int i = 0; i < cells.size(); i++) {
				if (cells.get(i).getRunway().equals(userDao.getRunway(runway))
						&& cells.get(i).getStage().equals(userDao.getStage(stage))) {
					cell = cells.get(i);
				}
			}
			cell.setPlan(plan);

			cell.setStage(userDao.getStage(stage));
			cell.setRunway(userDao.getRunway(runway));
			checkpoints.setDescription(description);
			checkpoints.setCell(cell);
			userDao.saveCheckpoint(checkpoints);
			sessionStatus.setComplete();
		}else{			
			System.out.println("deleting checkpoint..");
//			List<Checkpoints> allCheckpoint = userDao.getCheckpoints();
//			for(int i=0; i<allCheckpoint.size()-1; i++){
//				if(allCheckpoint.get(i).getId()==checkpoints.getId()){
//					allCheckpoint.remove(i);
//					allCheckpoint.s
//				}
//			}
			System.out.println("checkpoints.getCposition() :"+userDao.getCheckpointById(checkpoints.getId()).getCposition()); 
			checkpoints.setCposition(userDao.getCheckpointById(checkpoints.getId()).getCposition());  
			checkpoints.setCell(null); 
			userDao.saveCheckpoint(checkpoints);
			sessionStatus.setComplete();
		}
		
		
		return "redirect:adminview.html?id=" + plan_id;
	}

	@RequestMapping(value = "/editrunway.html", method = RequestMethod.GET)
	public String editrunway(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		Runway runway = userDao.getRunway(id);
		models.put("runway", runway);
		
		return "editrunway";
	}
	
	@RequestMapping(value = "/editrunway.html", method = RequestMethod.POST)
	public String editrunway(@RequestParam String description,
			@ModelAttribute Runway runway, HttpSession session,
			SessionStatus sessionStatus) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		FlightPlan plan = userDao.getPlans(plan_id);
		runway.setRposition(userDao.getRunway(runway.getId()).getRposition()); 
		System.out.println("plan id : "+plan_id);
		runway.setPlan(plan); 
		runway.setDescription(description); 
		userDao.saveRunway(runway);
		sessionStatus.setComplete();
		return "redirect:adminview.html?id=" + plan_id;
	}
	
	@RequestMapping(value = "/createplan.html", method = RequestMethod.GET)
	public String createplan(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		session.setAttribute("create_plan_department", id);
		models.put("flightplan", new FlightPlan());
		return "createplan";
	}

	@RequestMapping(value = "/editstage.html", method = RequestMethod.GET)
	public String editstage(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		Stage stage = userDao.getStage(id);
		models.put("stage", stage);
		
		return "editstage";
	}
	
	@RequestMapping(value = "/editstage.html", method = RequestMethod.POST)
	public String editstage(@RequestParam String sname,
			@ModelAttribute Stage stage, HttpSession session,
			SessionStatus sessionStatus) {
		Integer plan_id = (Integer) session.getAttribute("planid");
		FlightPlan plan = userDao.getPlans(plan_id);
		System.out.println("plan id : "+plan_id);
		stage.setPlan(plan); 
		stage.setSname(sname);  
		userDao.saveStage(stage);
		sessionStatus.setComplete();
		return "redirect:adminview.html?id=" + plan_id;
	}
	
	@RequestMapping(value = "/createplan.html", method = RequestMethod.POST)
	public String createplan(@ModelAttribute FlightPlan flightplan,
			HttpSession session) {
		FlightPlan plan = new FlightPlan();
		plan.setPublisheddate(new Date());
		plan.setName(flightplan.getName());
		
		System.out.println("department in session"+session.getAttribute("create_plan_department"));
		Integer id = (Integer) session.getAttribute("create_plan_department");
		plan.setDepartment(userDao.getDepartment(id));
		userDao.savePlan(plan);

		List<FlightPlan> planList = userDao.getAllPlans();
		FlightPlan lastPlan = planList.get(planList.size() - 1);

		return "redirect:adminview.html?id=" + lastPlan.getId();
	}

	@RequestMapping(value = "/departmenthome.html", method = RequestMethod.GET)
	public String departmenthome(@RequestParam Integer id, ModelMap models,
			HttpSession session) {
		session.setAttribute("departmentname", userDao.getDepartment(id)
				.getName());
//		session.setAttribute("departmentid", id);
		models.put("departmentid", id);
		models.put("planByDepartment", userDao.getPlansByDepartment(id));
		return "departmenthome";
	}
	
	 @RequestMapping(value = "/autocomplete.html")
	    public String users( @RequestParam String term, HttpServletResponse response )
	        throws JSONException, IOException
	    {
		 System.out.println("in auto controller");
		 
		 JSONArray jsonArray = new JSONArray();
	        List<User> users = userDao.searchUsersByPrefix( term, 10 );
	        for( User user : users )
	        {
	            Map<String, String> json = new HashMap<String, String>();
	            json.put( "id", user.getId().toString() );
	            json.put( "value", user.getFirst_name()+" "+user.getLast_name());
	            json.put( "label", user.getCin() + " " + user.getFirst_name()+" "+user.getLast_name() );
	            
	            jsonArray.put( json );
	        }
	        response.setContentType( "application/json" );
	        jsonArray.write( response.getWriter() );
	        return null;
	    }
	 
	 @RequestMapping(value ="/updateStageOrder.html")
	 public String updaterow(@RequestParam(required=false) Integer id,@RequestParam(required=false) Integer fromPosition,@RequestParam(required=false) Integer toPosition, HttpServletResponse response){
		 System.out.println("id :"+id);
		 System.out.println("fromPosition :"+fromPosition);
		 System.out.println("toPosition :"+toPosition);
		 Stage stage1 = userDao.getStage(id);
		 Integer currentPlan = stage1.getPlan().getId(); 
		 List<Stage> allStage = userDao.getStages(currentPlan); 
		 
		 for(int i=0; i< allStage.size(); i++){ 
			 if(fromPosition>toPosition){
				 if(allStage.get(i).getPosition()>=toPosition && allStage.get(i).getPosition()<fromPosition){
//					 System.out.println("in if loop");
					 allStage.get(i).setPosition(allStage.get(i).getPosition()+1); 
				 } 
			 }else{
				 if(allStage.get(i).getPosition()>= fromPosition && allStage.get(i).getPosition()<=toPosition){
//					 System.out.println("in else loop");
					 allStage.get(i).setPosition(allStage.get(i).getPosition()-1); 
				 }else{
//					 System.out.println("in else else loop");
//					 System.out.println("allStage.get(i).getPosition(): "+allStage.get(i).getPosition());

				 }
			 }
			 userDao.saveStage(allStage.get(i));
			  
		 }
		 stage1.setPosition(toPosition);
		 userDao.saveStage(stage1);
		 
//		 Stage stage2 = userDao.getStageByPosition(toPosition, currentPlan); 
//		 stage1.setPosition(toPosition);
//		 stage2.setPosition(fromPosition); 
//		 userDao.saveStage(stage1);
//		 userDao.saveStage(stage2);
		 return null;
	 }
	 @RequestMapping(value ="/updateCheckpointOrder.html")
	 @ResponseBody
	 public String updatecheckpointorder(@RequestParam(required=false) String str, @RequestParam(required=false) String newIndex){
		 
		 Integer id = Integer.parseInt(str);
		 Integer fromPosition = userDao.getCheckpointById(Integer.parseInt(str)).getCposition();
		 Integer toPosition = Integer.parseInt(newIndex)+1; 

//		 System.out.println("id :"+str);
//		 System.out.println("fromPosition :"+fromPosition);
//		 System.out.println("toPosition :"+toPosition);
		 Checkpoints cp1 = userDao.getCheckpointById(id);
		 Integer currentPlan = cp1.getCell().getPlan().getId();
		 List<Checkpoints> allCheckpoints = userDao.getCheckpointByPlan(currentPlan);
		 
		 for(int i=0; i< allCheckpoints.size(); i++){ 
			 if(fromPosition>toPosition){
				 if(allCheckpoints.get(i).getCposition()>=toPosition && allCheckpoints.get(i).getCposition()<fromPosition){
//					 System.out.println("in if loop");
					 allCheckpoints.get(i).setCposition(allCheckpoints.get(i).getCposition()+1); 
				 } 
			 }else{
				 if(allCheckpoints.get(i).getCposition()>= fromPosition && allCheckpoints.get(i).getCposition()<=toPosition){
//					 System.out.println("in else loop");
					 allCheckpoints.get(i).setCposition(allCheckpoints.get(i).getCposition()-1); 
				 }else{
//					 System.out.println("in else else loop");
//					 System.out.println("allStage.get(i).getPosition(): "+allCheckpoints.get(i).getCposition());
				 }
			 }
			 userDao.saveCheckpoint(allCheckpoints.get(i));
			 
		 }
		 cp1.setCposition(toPosition);
		 userDao.saveCheckpoint(cp1); 
		 
		 return null;
	 }
	 @RequestMapping(value ="/updateRunwayOrder.html")
	 public String updatestageorder(@RequestParam(required=false) String str, @RequestParam(required=false) String hello){
		 System.out.println("str :"+str); 
		 System.out.println("hello"+hello);  
//		 String splitter[] = str.split("\\s+");
//		 for(int i=0; i<splitter.length; i++){
//			 System.out.println("s1 :"+splitter[i]); 
//		 }
//		  
//		 FlightPlan currentPlan = userDao.getPlans(userDao.getRunway(Integer.parseInt(splitter[0])).getPlan().getId()); 
//		 System.out.println("currentplan :"+currentPlan.getName()); 
//		 List<Runway> runwayList = userDao.getRunwaysByPlanId(currentPlan.getId());
//		 System.out.println("arrayList :"+splitter.length);   
//		 System.out.println("runwayList :"+runwayList.size());  
//		 List<Runway> runwayList2 = new ArrayList<Runway>();
//		 for(int i=0; i<runwayList.size(); i++){
//			 Runway runway = userDao.getRunwayByPosition(Integer.parseInt(splitter[i]),currentPlan.getId());
//			 runway.setRposition(i+1); 
//			 runwayList2.add(runway);
//		 }
//		 for(int j=0; j<runwayList2.size(); j++){
//			 System.out.println("runwayList2 size :"+runwayList2.size());  
//			 userDao.saveRunway(runwayList2.get(j));
//			 System.out.println("Runway saved!!");  
//		 }
//		 runwayList2.clear();
		 return null;
	 }
}