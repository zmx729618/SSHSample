package org.nercita.bcp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class TempltUtil {  
  
    public static final String WORD_TEMPLATE = "/templateFile.ftl";   
    public static final String TEMPLATE_PATH = "/template";   
    public static final String PREVIEW_DOC = "/wordFile.doc";   
  
    public static Template configTemplate(HttpServletRequest request, String temp) throws IOException {   
	    Configuration config = new Configuration();   
	    ServletContext sc = request.getSession().getServletContext();   
	    config.setDirectoryForTemplateLoading(new File(sc.getRealPath(TEMPLATE_PATH)));   
	    config.setObjectWrapper(new DefaultObjectWrapper());   
	    Template template = config.getTemplate(temp, "UTF-8");   
        return template;   
        }   
  
    public static void toPreview(HttpServletRequest request, String temp, Map<?, ?> dataMap){   
        try {   
            String previewPath = request.getSession().getServletContext().getRealPath("")+PREVIEW_DOC;   
	        Template template = configTemplate(request, temp);   
	        FileOutputStream fos = new FileOutputStream(previewPath);   
	        Writer out = new OutputStreamWriter(fos, "UTF-8");   
            template.process(dataMap, out);   
            out.flush();   
            out.close();   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
}

