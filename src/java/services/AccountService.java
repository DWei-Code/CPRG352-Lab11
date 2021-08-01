package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

                /*
                String body = "Successful login by " + user.getFirstName() + " on " + (new java.util.Date()).toString();
                GmailService.sendMail(email, "Successful Login", body, false);
                 */
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());

                GmailService.sendMail(to, subject, template, tags);

                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public boolean forgotPassword(String email, String path) {
        UserDB userDB = new UserDB();
        boolean temp = false;
        try {
            User user = userDB.get(email);
            if (user.getEmail() != null) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Retrieved account to be recovered succesfully", email);

                String to = user.getEmail();
                String subject = "Notes keepr password recovery";
                String template = path + "/emailtemplates/passwordRecovery.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                tags.put("username", user.getEmail());
                tags.put("password", user.getPassword());

                GmailService.sendMail(to, subject, template, tags);
                temp = true;
            }
        } catch (Exception e) {
        }
        return temp;
    }
}
