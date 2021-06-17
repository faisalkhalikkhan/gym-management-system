/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afc.model;

/**
 *
 * @author LENOVO
 */
public class DataPasses {
    
    private static String adminName ;
    private static String otherData;

    public static String getAdminName() {
        return adminName;
    }

    public static void setAdminName(String adminName) {
        DataPasses.adminName = adminName;
    }

    public static String getOtherData() {
        return otherData;
    }

    public static void setOtherData(String otherData) {
        DataPasses.otherData = otherData;
    }
    
}
