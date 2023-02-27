package com.conapp.notes.workspace;

import java.io.File;

public interface WorkspaceViewToController {

    File[] getNotesList();

    boolean addNote(String name, String string);

    String getNote(String name);

    boolean deleteNote(String name);

    boolean isNameValid(String name);

}
