package com.conapp;

import java.util.Scanner;

public abstract class AbstractLoginView {
	public Scanner sc;
    protected String name;
    protected String username;
    protected String emailid;
    protected String password;
	
	protected AbstractLoginView()
	{
		sc = new Scanner(System.in);
	}
	
    protected void startLogin()
    {
        char choice = 0;
        do{
            System.out.println("----------------------");
            System.out.println("Enter ");
            System.out.println("1 - User Login");
            System.out.println("2 - Admin Login");
            System.out.println("3 - Sign up");
            System.out.println("0 - exit");
            	choice = sc.nextLine().charAt(0);
	            switch(choice)
	            {
	                case '1': login('u');
	                        break;
	                case '2': login('a');
	                		break;
	                case '3': signup();
	                        break;
	                case '0': System.out.println("Thanks for using our site!");
	                        break;
	                default: System.out.println("Invalid choice, try again.");
	            }
        }while(choice!='0');
    }
    
    abstract protected void login(char type);
    abstract protected void signup();

    protected String getEmailId()
    {
        System.out.print("Enter email id: ");
        return sc.nextLine();
    }

    protected String getUsername()
    {
        System.out.print("Enter username: ");
        return sc.nextLine();
    }

    
    protected String getPassword()
    {
        System.out.print("Enter password: ");
        return sc.nextLine();
    }
    
    public void signupSuccessful()
    {
        System.out.println("Added successfully!");
    }
}
