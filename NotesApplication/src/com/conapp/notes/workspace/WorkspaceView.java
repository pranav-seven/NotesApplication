package com.conapp.notes.workspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedHashSet;

import com.conapp.notes.dto.Notes;
import com.conapp.notes.dto.User;


public class WorkspaceView implements WorkspaceControllerToView {
    private WorkspaceViewToController workspaceController;
    private BufferedReader reader;

    public WorkspaceView(User user) throws IOException
    {
        workspaceController = new WorkspaceController(this, user);
        System.out.println("pOST-iT");
        reader = new BufferedReader(new InputStreamReader(System.in));
        openWorkspace();
    }

    // public static void main(String[] args) throws IOException{
    //     new WorkspaceView();
    // }

    void openWorkspace() throws IOException
    {
        char choice = 0;
        do{
            System.out.println("----------------------");
            viewNotes();
            System.out.println("----------------------");
            System.out.println("1 - Add note");
            System.out.println("2 - View a note");
            // System.out.println("3 - Edit note");
            System.out.println("3 - Delete note");
            System.out.println("0 - Log out");
            System.out.print("Enter choice: ");
            choice = reader.readLine().charAt(0);
            switch(choice)
            {
                case '1': addNote(); break;
                case '2': viewNote(); break;
                // case '4': editNote(); break;
                case '3': deleteNote(); break;
                case '0': break;
                default: System.out.println("Invalid choice, try again.");
            }
        }while(choice!='0');
    }

    private void viewNotes()
    {
        File[] list = workspaceController.getNotesList();
        if(list==null || list.length==0)
            System.out.println("Notes empty!");
        else
        {
            int i=1;
            System.out.println(String.format("%-26s", "Name")+"Last modified");
            for(File file : list)
            {
                String name = file.getName();
                System.out.println(String.format("%-26s", name.substring(0, name.indexOf('.')))+new Date(file.lastModified()));
            }
        }
    }

    private void addNote() throws IOException
    {
        System.out.println("Start typing, type \"!end\" in new line to stop.");
        StringBuilder builder = new StringBuilder();
        String string = "";
        do{
            string = reader.readLine();
            if(!string.equals("!end"))
                builder.append(string+"\n");
        }while(!string.equals("!end"));
        // System.out.println(builder);
        System.out.print("Do you want to save the notes? (Y/N): ");
        char choice = reader.readLine().charAt(0);
        if(choice=='Y' || choice=='y')
        {
            String name = "";
            boolean isValid = false;
            do{
                System.out.println("Enter name for notes: ");
                name = readName();
                isValid = workspaceController.isNameValid(name);
                if(!isValid)
                    printMessage("Name should contain only letters and digits!");
                else
                {
                    isValid = workspaceController.addNote(name, builder.toString());
                    if(!isValid)
                        printMessage("File name already exists!");
                }
            }while(!isValid);
        }
    }

    private String readName() throws IOException
    {
        String name;
        do{
            name = reader.readLine();
        }while(name==null);
        return name;
    }

    private void viewNote() throws IOException
    {
        System.out.println("Enter name of the note: ");
        String name = readName();
        String note = workspaceController.getNote(name);
        if(note!=null)
        {
            System.out.println("------------------");
            System.out.print(note);
            System.out.println("------------------");
            System.out.print("Enter \"Enter\" key to go back: ");
            reader.readLine();
        }
        else
            System.out.println("Note unavailable! :(");
    }

    private void editNote()
    {

    }
    
    private void deleteNote() throws IOException
    {
        viewNotes();
        System.out.println("Enter notes name: ");
        String name = reader.readLine();
        if(!workspaceController.deleteNote(name))
            printMessage("File does not exist");
    }

    public void printMessage(String message)
    {
        System.out.println(message);
    }
}
