package org.nercita.bcp.excel.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

/**
 * 导出工具类
 */
public class ExportUtil {
    public static String EXCEL_2007 = "Excel2007";
    public static String EXCEL_2003 = "Excel2003";
    private volatile Properties props = new Properties();
    private boolean inited = false;
    private volatile static ExportUtil util = new ExportUtil();

    private ExportUtil() {
        init();
    }

    public Properties getProps() {
        if (!inited)
            init();
        return props;
    }

    public String getProp(String key) {
        if (!inited)
            init();
        return props.getProperty(key);
    }

    public String getProp(String key, String defaultValue) {
        if (!inited)
            init();
        return props.getProperty(key, defaultValue);
    }


    private void init() {
        try {
            InputStream is = new ClassPathResource("export.properties").getInputStream();
            props.load(is);
            is.close();
            inited = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ExportUtil getUtil() {
        if (util == null)
            util = new ExportUtil();
        return util;
    }

    /**
     * 设置下载文件中文件的名称
     *
     * @param pFileName
     * @param request
     * @return
     */
    public static String encodeFilename(String pFileName, HttpServletRequest request){
    	String filename = null;    
        String agent = request.getHeader("USER-AGENT");
    	try {
	    	    
	        if (null != agent){    
	            if (-1 != agent.indexOf("Firefox")) {//Firefox    
	                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8"))))+ "?=";    
	            }else if (-1 != agent.indexOf("Chrome")) {//Chrome    
	                filename = new String(pFileName.getBytes(), "ISO8859-1");    
	            } else {//IE7+    
	                filename = URLEncoder.encode(pFileName, "UTF-8");    
	                filename = StringUtils.replace(filename, "+", "%20");//替换空格    
	            }    
	        } else {    
	            filename = pFileName;    
	        }    
	       
    	} catch (Exception ex) {

    	}
    	 return filename;
   }

    public static void writeResponse(String fileName, HttpServletRequest request, HttpServletResponse response, String version, String templatePath, Map<String, Object> beanParams) {
        writeResponse(fileName, request, response, version, templatePath, beanParams, false, null, null);
    }

    public static void writeResponse(String fileName, HttpServletRequest request, HttpServletResponse response, String version, String templatePath, Map<String, Object> beanParams,
                                     boolean innerCollectionAccess) {
        writeResponse(fileName, request, response, version, templatePath, beanParams, innerCollectionAccess, null, null);
    }

    public static void writeResponse(String fileName, HttpServletRequest request, HttpServletResponse response, String version, String templatePath, Map<String, Object> beanParams,
                                     boolean innerCollectionAccess, Map<Integer, List<CellRangeAddress>> rangeMap) {
        writeResponse(fileName, request, response, version, templatePath, beanParams, innerCollectionAccess, rangeMap, null);
    }

    public static void writeResponse(String fileName, HttpServletRequest request, HttpServletResponse response, String version, String templatePath, Map<String, Object> beanParams,
                                     Map<Integer, List<CellRangeAddress>> rangeMap, XLSTransformer transformer) {
        writeResponse(fileName, request, response, version, templatePath, beanParams, false, rangeMap, transformer);
    }

    public static void writeResponse(String fileName, HttpServletRequest request, HttpServletResponse response, String version, String templatePath, Map<String, Object> beanParams,
                                     boolean innerCollectionAccess, Map<Integer, List<CellRangeAddress>> rangeMap, XLSTransformer trans) {
        Assert.isTrue(StringUtils.isNotBlank(templatePath), "templatePath不可以为空字符串");
        XLSTransformer transformer = null;
        if (trans != null) {
            transformer = trans;
        } else {
            transformer = new XLSTransformer();
        }
        transformer.setJexlInnerCollectionsAccess(innerCollectionAccess);
        response.setCharacterEncoding("utf-8");
        if (EXCEL_2007.equals(version)) {
            fileName = encodeFilename(fileName + ".xlsx", request);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        } else {
            fileName = encodeFilename(fileName + ".xls", request);
//          templatePath = templatePath.substring(0, templatePath.length() - 1);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream is = null;
        try {
            if("linux".equals(System.getProperty("os.name").toLowerCase())){
                if(!templatePath.startsWith("/")){
                    templatePath="/"+templatePath;
                }
            }
            is = new FileInputStream(templatePath);
            Workbook book = transformer.transformXLS(is, beanParams);
            if (rangeMap != null) {
                Iterator<Integer> index = rangeMap.keySet().iterator();
                while (index.hasNext()) {
                    Integer i = index.next();
                    Sheet sheet = book.getSheetAt(i);
                    Iterator<CellRangeAddress> cra = rangeMap.get(i).iterator();
                    while (cra.hasNext()) {
                        sheet.addMergedRegion(cra.next());
                    }
                }
            }
            book.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    public static String getProperty(String property) {
        return getUtil().getProp(property);
    }
}
