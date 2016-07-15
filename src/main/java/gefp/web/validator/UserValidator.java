package gefp.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gefp.model.User;
import gefp.model.dao.UserDao;

@Component
public class UserValidator implements Validator {

	 @Autowired
     private UserDao userDao;
	 
	 
    @Override
    public boolean supports( Class<?> clazz )
    {
        return User.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate( Object target, Errors errors )
    {
        User user = (User) target;
       
        Pattern pattern;
  	  	Matcher matcher = null; 
  	  	
  	  String PASSWORD_PATTERN = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{4,}$";

        if(user.getPassword().equals("")){
    	}else{
    		pattern = Pattern.compile(PASSWORD_PATTERN);
    		matcher = pattern.matcher(user.getPassword());
    		if(matcher.matches()){
    			
    		}else{
    			errors.rejectValue( "password", "error.user.password.empty" );
    		}
    		
    	}
            
    }

}
