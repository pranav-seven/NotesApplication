package com.conapp.notes.login;

import java.io.IOException;

import com.conapp.AbstractLoginView;
import com.conapp.notes.dto.User;
import com.conapp.notes.workspace.WorkspaceView;

public class LoginView extends AbstractLoginView implements LoginControllerToViewCaller{
	
    private LoginViewToControllerCaller loginController;

    LoginView()
    {
    	super();
        System.out.println("pOST-iT");
        loginController = new LoginController(this);
    }

    public static void main(String[] args)
    {
        LoginView loginView = new LoginView();
        loginView.startLogin();
    }

    protected void startLogin()
    {
        char choice = 0;
        do{
            System.out.println("----------------------");
            System.out.println("Enter ");
            System.out.println("1 - Log In");
            System.out.println("2 - Sign up");
            System.out.println("0 - exit");
            	choice = sc.nextLine().charAt(0);
	            switch(choice)
	            {
	                case '1': login('u');
	                        break;
	                case '2': signup();
	                        break;
	                case '0': System.out.println("Nandri! Meendum Varuga!");
	                        break;
	                default: System.out.println("Invalid choice, try again.");
	            }
        }while(choice!='0');
    }

//Login *******************************
    @Override
    protected void login(char type)
    {
     //   sc.next();
        username = super.getUsername();
        password = super.getPassword();
        loginController.checkLoginDetailsOnline(type, username, password);
    }

    @Override
    public void loginSuccess(User user, char type)
    {
        System.out.println("---"+user.getName()+"'s notes---");
        try{
            new WorkspaceView(user);
        }catch(IOException io)
        {
            System.out.println(io);
        }
    }

    public void loginFailure(char type)
    {
        System.out.println("Username or password invalid!\nTry again");
        login(type);
    }
    
    //Signup *******************************
    @Override
    protected void signup()
    {
        // emailid = getEmailId();
        username = getUsername();
        if(loginController.checkUsername(username))
        {
            System.out.print("Name: ");
            name = sc.nextLine();
            do{
                password = getPassword();
                System.out.println("Re-enter password: ");
                String rePassword = sc.nextLine();
                if(loginController.checkNewPassword(password, rePassword))
                {
                    loginController.addUser(name, username, password);
                    break;
                }
                else
                    System.out.println("Passwords do not match");
            }while(true);
        }
        else
            System.out.println("Username already exists.");
    }

    //    super call happens
    //    public void signupSuccessful()
    //    {
    //        System.out.println("Added successfully!");
    //    }

    //input methods *****************
    
    protected String getUsername()
    {
       String username = super.getUsername();
       if(!loginController.isUsernameValid(username))
       {
            System.out.println("Enter a valid username!");
            System.out.println("(username must contain only letters (atleast one), numbers, dots or underscores)");
            username = getUsername();
       }
       return username;
    }

    protected String getPassword()
    {
        String password = super.getPassword();
        if(!loginController.isPasswordValid(password))
       {
            System.out.println("Enter a valid password!");
            System.out.println("(password must contain atleast eight characters, one uppercase letter, one lowercase letter and one digit)");
            password = getPassword();
       }
       return password;
    }


}
