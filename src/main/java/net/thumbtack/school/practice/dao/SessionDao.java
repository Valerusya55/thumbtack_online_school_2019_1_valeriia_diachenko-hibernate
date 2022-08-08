package net.thumbtack.school.practice.dao;

import net.thumbtack.school.practice.exeption.ServerException;
import net.thumbtack.school.practice.model.Session;
import net.thumbtack.school.practice.model.User;

public interface SessionDao {
    void addToken(Session session) throws ServerException;
    User getUserByToken(String token) throws ServerException;
    void deleteAll();
}
