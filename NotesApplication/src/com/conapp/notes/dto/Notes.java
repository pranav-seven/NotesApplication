package com.conapp.notes.dto;

public class Notes {
    private String extension;
    private String name;
    private String note;

    public Notes(String name, String note)
    {
        extension = ".txt";
        this.name = name;
        this.note = note;
        System.out.println(note);
        if(note.equals(null))
            note = "";
    }

    public String getName()
    {
        return name;
    }

    public String getNote()
    {
        return note;
    }
}
