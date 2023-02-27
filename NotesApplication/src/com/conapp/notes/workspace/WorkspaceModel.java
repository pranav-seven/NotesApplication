package com.conapp.notes.workspace;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;

import com.conapp.notes.dto.Notes;
import com.conapp.notes.dto.User;
import com.conapp.notes.repository.NotesRepository;

public class WorkspaceModel implements WorkspaceControllerToModel{

    private NotesRepository repo;
    private WorkspaceModelToController workspaceController;

    WorkspaceModel(WorkspaceModelToController workspaceController, User user)
    {
        this.workspaceController = workspaceController;
        repo = NotesRepository.getInstance();
        repo.setPath(user);
    }

    @Override
    public File[] getNotes() {
        return repo.getNotesList();
    }

    @Override
    public boolean addNote(String name, String string) {
        return repo.addNote(name, string);
    }

    @Override
    public String getNote(String name) {
        return repo.getNote(name);
    }

    @Override
    public boolean deleteNote(String name) {
        return repo.deleteNote(name);
    }

}
