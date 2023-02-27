package com.conapp;

import java.util.regex.Pattern;

abstract public class AbstractLoginController {
    public static boolean isEmailIdValid(String emailid)
    {
        // if(emailid.length()<5)
        //     return false;
        // boolean prevChar = Character.isLetterOrDigit(emailid.charAt(0));
        // if(!prevChar)
        //     return false;
        // boolean isValid = true;
        // boolean ampersand = false;
        // boolean dot = false;
        // char[] id = emailid.toCharArray();
        // for(int i=1; i<id.length; i++)
        // {
        //     if(id[i]=='@')
        //     {
        //         if(!prevChar || ampersand)
        //         {
        //             isValid = false;
        //             break;
        //         }
        //         else
        //             ampersand = true;
        //     }
        //     else if(id[i]=='.')
        //     {
        //         if(!prevChar || !ampersand || dot)
        //         {
        //             isValid = false;
        //             break;
        //         }
        //         else
        //             dot = true;
        //     }
        //     else if(!(prevChar = Character.isLetterOrDigit(id[i])))
        //     {
        //         isValid = false;
        //         break;
        //     }
        // }
        // if(!Character.isLetter(id[id.length-1]))
        //     isValid=false;
        // return isValid;
        return Pattern.matches("[a-z0-9]{5,}@[a-z]{2,}.[a-z]{2,4}", emailid);
    }

    public static boolean isusernameValid(String username)
    {
        boolean isValid = true;
        boolean letter = false;
        char[] id = username.toCharArray();
        for(int i=0; i<id.length; i++)
        {
            if(!(Character.isLetterOrDigit(id[i]) || id[i]=='.' || id[i]=='_'))
            {
                isValid = false;
                break;
            }
            if(!letter && Character.isLetter(id[i]))
                letter = true;
        }
        if(!letter)
            isValid = false;
        return isValid;
    }

    public static boolean ispasswordValid(String password)
    {
        if(password.length()<8)
            return false;
        boolean isValid = true;
        boolean upperCase = false;
        boolean lowerCase = false;
        boolean digit = false;
        char[] id = password.toCharArray();
        for(int i=0; i<id.length; i++)
        {
            if(Character.isLowerCase(id[i]))
                lowerCase = true;
            else if(Character.isUpperCase(id[i]))
                upperCase = true;
            else if(Character.isDigit(id[i]))
                digit = true;
            if(upperCase && lowerCase && digit)
                break;
        }
        if(!(upperCase && lowerCase && digit))
            isValid = false;
        return isValid;
    }
}
