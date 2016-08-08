/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Virginie
 */
@Entity
public class Password extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAS_SEQ")
    @SequenceGenerator(name = "PAS_SEQ", 
            sequenceName = "password_id_seq", 
            initialValue = 100)
    protected Long id;

    private String hashed;
    private String salt;

    public String getHashed() {
        return hashed;
    }

    public void setHashed(String hashed) {
        this.hashed = hashed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
