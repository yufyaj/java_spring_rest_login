package com.test.java_spring_rest_login.service;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.java_spring_rest_login.model.UserSessionEntity;
import com.test.java_spring_rest_login.repository.UserSessionRepository;

@Service
public class UserSessionService {
    @Autowired
    UserSessionRepository sessionRepos;

    public UserSessionEntity create(String userId){
        UserSessionEntity newSession   = new UserSessionEntity();
        String        newSessionId = "";
        Optional<UserSessionEntity> opOldSession = sessionRepos.findByUserId(userId);

        if (!opOldSession.isEmpty()){
            UserSessionEntity oldSession = opOldSession.get();
            if (isEnableSession(oldSession.getSessionId())){
                update(oldSession.getSessionId());
                return oldSession;
            }
        }

        while (true) {
            newSessionId = createSessionId();
            if (sessionRepos.findBySessionId(newSessionId).isEmpty()) {
                break;
            }
        }

        newSession.setSessionId(newSessionId);
        newSession.setUserId(userId);
        newSession.setStartDate(new Date());
        newSession.setLimitDate(returnLimitDate());
        sessionRepos.save(newSession);

        return newSession;
    }

    public boolean isEnableSession(UserSessionEntity session){
        Date limitDate        = session.getLimitDate();
        Date nowDate          = new Date();

        if (limitDate.compareTo(nowDate) >= 0) {
            return true;
        }

        sessionRepos.delete(session);
        return false;
    }


    public boolean isEnableSession(String sessionId){
        Optional<UserSessionEntity> oSession = sessionRepos.findBySessionId(sessionId);

        if (oSession.isEmpty()){
            return false;
        }

        UserSessionEntity session = oSession.get();
        Date limitDate        = session.getLimitDate();
        Date nowDate          = new Date();

        if (limitDate.compareTo(nowDate) >= 0) {
            return true;
        }

        sessionRepos.delete(session);
        return false;
    }

    public boolean update(UserSessionEntity session){
        session.setLimitDate(returnLimitDate());
        sessionRepos.saveAndFlush(session);

        return true;
    }

    public boolean update(String sessionId){
        Optional<UserSessionEntity> oSession = sessionRepos.findBySessionId(sessionId);

        if (oSession.isEmpty()){
            return false;
        }

        UserSessionEntity session = oSession.get();
        session.setLimitDate(returnLimitDate());
        sessionRepos.saveAndFlush(session);

        return true;
    }

    private Date returnLimitDate(){
        final int min = 30;

        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MINUTE, min);
        return cal.getTime();
    }

    private String createSessionId(){
        final int    len   = 20;
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom  random = new SecureRandom();
        StringBuilder strId  = new StringBuilder();

        for (int i = 0; i < len; i++){
            int index = random.nextInt(chars.length());
            strId.append(chars.charAt(index));
        }

        return strId.toString();
    }
}
