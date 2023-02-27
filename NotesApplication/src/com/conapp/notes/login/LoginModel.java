package com.conapp.notes.login;

import com.conapp.notes.dto.User;
import com.conapp.notes.repository.NotesRepository;

import java.util.List;

public class LoginModel implements LoginControllerToModelCaller {
	private LoginModelToControllerCaller loginController;
	private NotesRepository repo;

	LoginModel(LoginController controller)
	{
		loginController = controller;
		repo = NotesRepository.getInstance();
	}

	public boolean checkUsername(String username)
	{
		List<User> list = repo.getUsers();
		for(User user : list)
		{
			if(user.getUsername().equals(username))
				return false;
		}
		return true;
	}

    public void addUser(String name, String username, String password)
	{
		repo.addUser(name, username, password);
		loginController.signupSuccessful();
	}

    public List<User> getUsersList()
    {
        return repo.getUsers();
    }
}
