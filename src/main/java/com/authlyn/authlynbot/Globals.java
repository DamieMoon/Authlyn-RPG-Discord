package com.authlyn.authlynbot;

public class Globals {
    //working Directory
    public static String wdir = "";

    private static String OS = null;
    public static String getOsName()
    {
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }
    public static boolean isWindows()
    {
        return getOsName().startsWith("Windows");
    }

    public static boolean isLinux(){
        return getOsName().startsWith("Linux");
    }

    public static boolean isMac(){
        return getOsName().startsWith("Mac");
    }
     public static void setWdir(){
        if(isWindows()){
            System.out.println(System.getProperty("user.dir"));
            //wdir = System.getProperty("user.dir") + "\\src\\main\\java\\com\\authlyn\\authlynbot";
        }else if(isLinux()){
            System.out.println(System.getProperty("user.dir"));
        }else if(isMac()){
            System.out.println(System.getProperty("user.dir"));
        }
     }

    public static void main(String[] args) {

    }


}
