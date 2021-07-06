package reference.util;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by JaeCheol.
 * FileName : FileDownloadView
 * Date: 2019-10-22
 * Time: 오전 10:02
 */
public class FileDownloadView extends AbstractView {

    Logger logger = LoggerFactory.getLogger(FileDownloadView.class);

    @Autowired
    private MessageSource messageSource;

    public FileDownloadView() {
        //content type을 지정.
        super.setContentType("application/octet-stream");
    }

    @Override
    public void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest req, HttpServletResponse res) throws Exception {

        File file = (File) model.get("downloadFile");

        if (file != null && file.exists()) {

            String ori_fileName = getDisposition(file.getName(), getBrowser(req));

            res.setContentType(super.getContentType());
            res.setContentLength((int) file.length());
            res.setHeader("Content-Transfer-Encoding", "binary");
            res.setHeader("Content-Disposition", "attachment;fileName=\"" + ori_fileName + "\";");

            OutputStream out = res.getOutputStream();

            FileInputStream fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
            fis.close();
            out.flush();
        } else {
            throw new FileNotFoundException();
        }

    }

    /*브라우져 버전 확인*/
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");

        String browser = null;

        if (!StringUtils.isEmpty(header)) {
            if (header.indexOf("MSIE") > -1) {
                browser = "MSIE";
            } else if (header.indexOf("Chrome") > -1) {     // 크롬
                browser = "Chrome";
            } else if (header.indexOf("Safari") > -1) {    // 사파리
                browser = "Safari";
            } else if (header.indexOf("Trident/7.0") > -1) { //IE 11 이상
                browser = "MSIE";
            } else if (header.indexOf("Trident/6.0") > -1) { //IE 10 이상
                browser = "MSIE";
            } else if (header.indexOf("Trident/5.0") > -1) { //IE 9 이상
                browser = "MSIE";
            } else if (header.indexOf("Trident/4.0") > -1) { //IE 8 이상
                browser = "MSIE";
            } else {
                browser = "Firefox";
            }

        }
        return browser;

    }

    /*브라우저별 인코딩 처리*/
    private String getDisposition(String filename, String browser) throws Exception {
        String encodedFilename = null;
        if (StringUtils.equals(browser, "MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (StringUtils.equals(browser, "Firefox")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (StringUtils.equals(browser, "Safari")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (StringUtils.equals(browser, "Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            throw new RuntimeException("Not supported browser");
        }
        return encodedFilename;
    }

}