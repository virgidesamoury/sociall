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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virginie
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Image.findByArtifact", query = "SELECT i FROM Image i WHERE i.artifact = :artifact")
})
public class Image extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMA_SEQ")
    @SequenceGenerator(name = "IMA_SEQ", 
            sequenceName = "image_id_seq", 
            initialValue = 100)
    protected Long id;

    @Version
    private Long version;

    private byte[] bytes;
    private String fileName;
    private String alt;
    private String title;
    
    @OneToOne(mappedBy = "profilePic")
    private User user;
    
    @XmlTransient
    @ManyToOne
    private Artifact artifact;
    
    @Override
    public Long getId() {
        return this.id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public void setArtifact(Artifact artifact) {
        this.artifact = artifact;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    // http://java.dzone.com/articles/jpa-implementation-patterns-2
    @PreRemove
    public void preRemove(){
        this.user.setProfilePic(null);
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", version=" + version + ", bytes=" + bytes + ", fileName=" + fileName + ", alt=" + alt + ", title=" + title + ", user=" + user + '}';
    }
    
}
