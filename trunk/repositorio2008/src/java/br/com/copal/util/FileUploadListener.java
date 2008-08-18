/*
 * FileUploadListener.java
 *
 * Created on 3 de Março de 2008, 07:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.copal.util;

import org.apache.commons.fileupload.ProgressListener;

/**
 *
 * @author Nhandeara
 */
public class FileUploadListener implements ProgressListener{
    private volatile long
            bytesRead = 0L,
            contentLength = 0L,
            item = 0L;
    
    /** Creates a new instance of FileUploadListener */
    public FileUploadListener() {
        super();
    }
    
    public void update(long aBytesRead, long aContentLength, int aItem){
        bytesRead = aBytesRead;
        contentLength = aContentLength;
        item = aItem;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public long getItem() {
        return item;
    }
    
    
    
}
