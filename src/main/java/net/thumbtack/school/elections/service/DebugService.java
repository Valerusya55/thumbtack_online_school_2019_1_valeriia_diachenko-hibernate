package net.thumbtack.school.elections.service;

import com.google.gson.Gson;

import net.thumbtack.school.practice.dao.DebugDao;
import net.thumbtack.school.practice.daoimpl.DebugDaoImpl;


public class DebugService {
    private DebugDao debugDao;
    private static Gson gson;

    public DebugService() {
        debugDao = new DebugDaoImpl();
    }


    public void clearDataBase() {
        debugDao.clearDataBase();
    }
}
