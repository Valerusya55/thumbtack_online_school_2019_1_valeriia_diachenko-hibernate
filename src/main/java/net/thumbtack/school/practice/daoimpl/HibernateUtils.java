package net.thumbtack.school.practice.daoimpl;

import net.thumbtack.school.practice.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            Configuration conf = new Configuration();
            conf.addAnnotatedClass(User.class);
            conf.addAnnotatedClass(Candidate.class);
            conf.addAnnotatedClass(EvaluationProposal.class);
            conf.addAnnotatedClass(Proposal.class);
            conf.addAnnotatedClass(Session.class);
            return conf.configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.print(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
