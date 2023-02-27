package com.conapp.notes.login;

import com.conapp.notes.dto.User;

public interface LoginControllerToViewCaller {
	void signupSuccessful();
	void loginSuccess(User user, char type);
	void loginFailure(char type);
}
