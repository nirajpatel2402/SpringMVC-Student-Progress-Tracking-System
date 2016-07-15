package gefp.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false, unique = true)
    private String cin;
	
    @Column(nullable = false, unique = true)
    private String username;

	@Column(nullable = false)
    private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	
    private String first_name;

    private String last_name;
    
    @Column(nullable = false)
    private boolean enabled;
    
    @ManyToMany
    Set<Roles> roles;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER) 
    List<Checkpoints> checkpoints;

    @ManyToOne
    Department major;

    @ManyToOne
    FlightPlan plan;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public List<Checkpoints> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Checkpoints> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	

	public Department getMajor() {
		return major;
	}

	public void setMajor(Department major) {
		this.major = major;
	}

	
	
	public FlightPlan getPlan() {
		return plan;
	}

	public void setPlan(FlightPlan plan) {
		this.plan = plan;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isAdmin(){
		return roles.contains("administrators");
	}
	
	public boolean isStudent(){
		return roles.contains("student");
	}
	
	public boolean isAdvisor(){
		return roles.contains("advisor");
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( Roles role : getRoles() )
            authorities.add( new GrantedAuthorityImpl( role.getRolename() ) );
        return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}	
	
	
}
