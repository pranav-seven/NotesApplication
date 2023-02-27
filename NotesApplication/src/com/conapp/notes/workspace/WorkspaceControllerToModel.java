package com.conapp.notes.workspace;

import java.io.File;

public interface WorkspaceControllerToModel {

    File[] getNotes();

    boolean addNote(String name, String string);

    String getNote(String name);

    boolean deleteNote(String name);

}
