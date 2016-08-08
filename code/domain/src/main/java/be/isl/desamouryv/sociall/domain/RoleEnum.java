/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.domain;

/**
 *
 * @author Virginie
 */
public enum RoleEnum {
    
    ADMIN(1L, "ADMIN"),
    MEMBER(2L, "MEMBER");

    private RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public static RoleEnum findForName(String string){
        RoleEnum[] values = RoleEnum.values();
        for (RoleEnum roleEnum : values) {
            if(roleEnum.name.equals(string))
                return roleEnum;
        }
        return null;
    }
    
}
