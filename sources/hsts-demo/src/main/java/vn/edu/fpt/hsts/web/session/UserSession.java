/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts.web.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "SESSION")
public class UserSession {
    /**
     * The ID.
     */
    private int id;
    /**
     *
     */
    private String username;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
