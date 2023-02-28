package com.conapp.notes.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import com.conapp.notes.dto.User;

public class NotesRepository {
    private static NotesRepository repo;
    private File folder;
    private File userFile;
    private String path;
    private File[] fileList;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Comparator<File> comparator;
    private List<User> userList;

    private NotesRepository()
    {
        userList = new ArrayList<>();
        userFile = new File("C:\\Users\\_______\\eclipse-workspace\\FlightTicketBooking\\src\\com\\conapp\\notes\\repository\\userfile.txt");
        //give the user folder name in the dash
        try{
            userFile.createNewFile();
            reader = new BufferedReader(new FileReader(userFile));
            String data = "";
            String[] arguments = null;
            Pattern pattern = Pattern.compile(",");
            do{
                data = reader.readLine();
                if(data!=null){
                    arguments = pattern.split(data);
                    userList.add(new User(arguments[0], arguments[1], arguments[2]));
                }
            }while(data!=null);
            reader.close();
        }catch(IOException ioe) {
            System.out.println(ioe);
        }
        System.out.println(userList);
        // userList.add(new User("Pranav", "pranav@gmail.com", "pranav7", "Passw0rd"));
        // userList.add(new User("Sanjay Ramasami", "sanjay@airvoice.com", "airvoiceceo", "short-term0"));
    }

    public void setPath(User user)
    {
        path = "C:\\Users\\_______\\eclipse-workspace\\FlightTicketBooking\\src\\com\\conapp\\notes\\repository\\"+user.getUsername()+"\\";
        //give the user folder name in the dash
        folder = new File(path);
        folder.mkdir();
        fileList = folder.listFiles();
        comparator = new Comparator<File>(){
                        public int compare(File file1, File file2){
                            return file1.lastModified()>file2.lastModified()?-1:1;
                        }
                    };
        if(fileList!=null)
        Arrays.sort(fileList, comparator);
    }

    public static NotesRepository getInstance()
    {
        if(repo==null)
            repo = new NotesRepository();
        return repo;
    }

    public boolean addNote(String name, String string) {
        File file = new File(path+name+".txt");
        if(file.exists())
        {
            System.out.println("============");
            return false;
        }
        System.out.println("out");
        try{
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.close();
        }catch(IOException ioe)
        {
            System.out.println(ioe);
        }
        fileList = folder.listFiles();
        Arrays.sort(fileList, comparator);
        return true;
    }

    public String getNote(String name) {
        File file = new File(path+name+".txt");
        String note = null;
        if(file.exists())
        {
            try{
                reader = new BufferedReader(new FileReader(file));
                StringBuilder builder = new StringBuilder();
                String temp = null;
                do{
                    temp = reader.readLine();
                    if(temp!=null)
                        builder.append(temp+"\n");
                }while(temp!=null);
                note = builder.toString();
                reader.close();
            }catch(IOException ioe)
            {
                System.out.println(ioe);
            }
        }
        return note;
    }

    public File[] getNotesList()
    {
        return fileList;
    }

    public boolean deleteNote(String name) {
        File file = new File(path+name+".txt");
        if(!file.exists())
            return false;
        file.delete();
        fileList = folder.listFiles();
        Arrays.sort(fileList, comparator);
        return true;
    }

    public List<User> getUsers() {
        return userList;
    }

    public void addUser(String name, String username, String password) {
        userList.add(new User(name, username, password));
        try{
            writer = new BufferedWriter(new FileWriter(userFile, true));
            writer.newLine();
            writer.append(name+","+username+","+password);
            writer.close();
        }catch(IOException ioe)
        {
            System.out.println(ioe);
        }
    }


    
}
