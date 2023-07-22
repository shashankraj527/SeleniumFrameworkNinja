package Experiments.Demo;

import java.util.Date;

public class Demo {
    public static void main(String[] args){
        Date date=new Date();
        /*
        Sun_Jul_16_14_26_52_IST_2023
         */
        System.out.println(date.toString().replace(" ","_").replace(":","_"));
        /*
        String datetext=date.toString();
        String dateTextWithoutSpaces=datetext.replace(" "," _");
        String dateTextWithoutSpacesAndColon=dateTextWithoutSpaces.replace(":"," ");
        System.out.println(dateTextWithoutSpacesAndColon);
        */
    }
}
