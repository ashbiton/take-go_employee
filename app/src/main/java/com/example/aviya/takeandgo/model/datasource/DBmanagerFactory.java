package com.example.aviya.takeandgo.model.datasource;

import com.example.aviya.takeandgo.model.backend.DB_manager;

/**
 * Created by aviya on 01/12/2017.
 */

public class DBmanagerFactory {

    public static DB_manager getManager()
    {
        return new MySQL_DBmanager();
    }
}
