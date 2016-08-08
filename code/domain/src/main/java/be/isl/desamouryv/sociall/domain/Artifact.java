/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virginie Desamoury <vdesamoury@gmail.com>
 */
@XmlRootElement
@XmlSeeAlso({Event.class, Recipe.class, Place.class, Product.class})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Artifact.findLatest", query = "SELECT a FROM Artifact a ORDER BY a.createdOn DESC")
})
public abstract class Artifact extends Bean {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ART_SEQ")
    @SequenceGenerator(name = "ART_SEQ", 
            sequenceName = "artifact_id_seq", 
            initialValue = 100)
    protected Long id;

    @Version
    private Long version;
    
    private String title;
    
    @Column(length = 2550)
    private String description;
    private String url;
    
    @XmlTransient
    @OneToMany(mappedBy = "artifact")
    private List<Image> images;

    @XmlTransient
    @OneToMany(mappedBy = "artifact")
    private List<Review> reviews;
    
    @ManyToMany
    private List<Tag> tags;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Artifact{" + "id=" + id + ", version=" + version + ", title=" + title + ", description=" + description + ", url=" + url + ", images=" + images + ", reviews=" + reviews + ", tags=" + tags + '}';
    }
    
}
