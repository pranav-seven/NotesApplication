package com.conapp.notes.workspace;

import java.io.File;
import java.util.regex.Pattern;

import com.conapp.notes.dto.User;


public class WorkspaceController implements WorkspaceViewToController, WorkspaceModelToController{

    private WorkspaceControllerToView workspaceView;
    private WorkspaceControllerToModel workspaceModel;

    WorkspaceController(WorkspaceControllerToView  workspaceView, User user)
    {
        this.workspaceView = workspaceView;
        this.workspaceModel = new WorkspaceModel(this, user);
    }

    @Override
    public File[] getNotesList() {
        return workspaceModel.getNotes();
    }

    @Override
    public boolean addNote(String name, String string) {
        return workspaceModel.addNote(name, string);
    }

    @Override
    public String getNote(String name) {
        return workspaceModel.getNote(name);
    }

    @Override
    public boolean deleteNote(String name) {
        return workspaceModel.deleteNote(name);
    }

    @Override
    public boolean isNameValid(String name) {
        return Pattern.matches("([a-z]|[A-Z]|[0-9])+", name);
    }

}
