/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virginie
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@NamedQueries({
    @NamedQuery(name = "Tag.findAll", query = "SELECT i FROM Tag i"),
    @NamedQuery(name = "Tag.findContaining", query = "SELECT i FROM Tag i WHERE LOWER(i.name) LIKE :query")
})
public class Tag extends Bean{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USE_SEQ")
    @SequenceGenerator(name = "USE_SEQ",
            sequenceName = "user_id_seq",
            initialValue = 100)
    private Long id;
    
    @Column(name="NAME_")
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "be.isl.desamouryv.sociall.domain.Tag[ id=" + id + " ]";
    }
    
}
