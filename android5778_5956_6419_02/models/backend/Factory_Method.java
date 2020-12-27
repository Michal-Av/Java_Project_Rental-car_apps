package com.example.talia.android5778_5956_6419_02.models.backend;


//import com.example.talia.android5778_5956_6419_02.models.datasource.List_DBManager;
import com.example.talia.android5778_5956_6419_02.models.datasource.MySQL_DBManager;

/**
 * Created by michalus.av on 22/01/2018.
 */

public class Factory_Method {
    static MySQL_DBManager ibackend=null;
    public static MySQL_DBManager getBackend()
    {
        if(ibackend==null)
            //ibackend=new List_DBManager();
            ibackend=new MySQL_DBManager();
        return ibackend;
    }
}