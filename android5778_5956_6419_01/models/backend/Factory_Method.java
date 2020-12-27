package com.example.talia.android5778_5956_6419_01.models.backend;

//import com.example.talia.android5778_5956_6419_01.models.datasources.List_DBManager;
import com.example.talia.android5778_5956_6419_01.models.datasources.MySQL_DBManager;

/**
 * Created by michalus.av on 26/12/2017.
 */

public class Factory_Method {
    static IDBManager ibackend=null;
    public static IDBManager getBackend()
    {
        if(ibackend==null)
            //ibackend=new List_DBManager();
            ibackend=new MySQL_DBManager();
        return ibackend;
    }
}
