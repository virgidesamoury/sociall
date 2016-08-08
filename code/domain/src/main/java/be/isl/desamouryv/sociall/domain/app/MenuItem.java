/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain.app;

import be.isl.desamouryv.sociall.domain.Bean;
import be.isl.desamouryv.sociall.domain.Role;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Virginie
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query="SELECT m FROM MenuItem m ORDER BY m.index ASC")
})
public class MenuItem extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEN_SEQ")
    @SequenceGenerator(name = "MEN_SEQ",
            sequenceName = "menu_id_seq",
            initialValue = 100)
    protected Long id;
    
    private int index;
    private String filePath;
    private String title;
    @OneToMany
    private List<Role> rolesRestriction;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Role> getRolesRestriction() {
        return rolesRestriction;
    }

}
