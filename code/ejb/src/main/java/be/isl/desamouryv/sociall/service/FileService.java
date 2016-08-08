/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.service;

import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Local;

/**
 *
 * @author Virginie
 */
@Local
public interface FileService {

    public String storeFile(InputStream input, String fileName, String uploadPath) throws IOException;

    public void deleteFile(String fileName, String path) throws Exception;
    
}
