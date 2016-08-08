/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virginie
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "USER_")
@NamedQueries({
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE LOWER(u.email) like LOWER(:email)")
})
public class User extends Bean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USE_SEQ")
    @SequenceGenerator(name = "USE_SEQ",
            sequenceName = "user_id_seq",
            initialValue = 100)
    protected Long id;

    @Version
    private Long version;

    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    private boolean active;

    @XmlTransient
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Image profilePic;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG")
    private Language language;

    @XmlTransient
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Password password;

    @OneToOne(orphanRemoval = true)
    private Address address;

    
    @XmlTransient
    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    
    @XmlTransient
    @OneToMany(mappedBy = "sender")
    private List<Contact> sentInvites;

    @XmlTransient
    @OneToMany(mappedBy = "invited")
    private List<Contact> receivedInvites;
    

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", email=" + email + ", active=" + active
                + ", profilePic=" + (profilePic == null ? "null" : profilePic.getFileName()) + ", language=" + language + ", password=" + password + '}';
    }

}
