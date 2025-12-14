package org.example.core;

import org.example.adapter.DatabaseAdapter;
import org.example.facade.HospitalFacade;

/**
 * Singleton holding application-wide services and configuration.
 */
public class AppContext {
    private static final AppContext INSTANCE = new AppContext();

    private final DatabaseAdapter databaseAdapter;
    private final HospitalFacade hospitalFacade;

    private AppContext() {
        this.databaseAdapter = new DatabaseAdapter();
        this.hospitalFacade = new HospitalFacade(databaseAdapter);
    }

    public static AppContext getInstance() {
        return INSTANCE;
    }

    public DatabaseAdapter getDatabaseAdapter() {
        return databaseAdapter;
    }

    public HospitalFacade getHospitalFacade() {
        return hospitalFacade;
    }
}
