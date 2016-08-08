/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.desamouryv.sociall.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Virginie
 */
@Stateless
public class FileServiceImpl implements FileService {

    private static final Logger logger = Logger.getLogger(FileServiceImpl.class.getName());
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public String storeFile(InputStream input, String fileName, String uploadPath) throws IOException {
        logger.log(Level.INFO, "uploadPath: {0}", uploadPath);

        String prefix = FilenameUtils.getBaseName(fileName).replaceAll(" ", "");
        String suffix = FilenameUtils.getExtension(fileName);

        File tempFile = File.createTempFile(prefix + "-", "." + suffix, new File(uploadPath));
        OutputStream output = new FileOutputStream(tempFile);

        try {
            IOUtils.copy(input, output);
        } finally {
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(input);
        }
        logger.log(Level.INFO, "file uploaded at: {0}", tempFile.getAbsolutePath());
        return tempFile.getName();
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public void deleteFile(String fileName, String path) throws Exception{
        logger.log(Level.INFO, "delete file: {0}...", fileName);
        File file = new File(path+"\\"+fileName);
        boolean delete = file.delete();
        logger.log(Level.INFO, "... file deleted: {0}", delete);
        if(!delete){
            throw new Exception("Failed to delete file");
        }
    }
}
