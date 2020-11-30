package com.lanyoumobility.mobility_webview.utils.doc;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.lanyoumobility.mobility_webview.utils.Config;
import com.lanyoumobility.mobility_webview.utils.L;

import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************
 * 通过word模板生成新的word工具类
 * @Package com.cccuu.project.myUtils
 * @Author duan
 * @Date 2018/3/29 14:24
 * @Version V1.0
 *******************************************/
public class WordUtils {


    private static String DOC_TEMPLET_3 = "TEMPLET_1.docx";// 模版1


    /**
     * 根据模板生成word
     * @param context
     * @param projectName
     * @param imageNumber
     * @param designer
     * @param progress
     * @param date
     * @param manage
     * @param description
     * @param prjImage
     * @param partyAFile
     * @return
     */
    public boolean  writeToWord(Context context, String projectName, String imageNumber, String designer, String progress, String date, String manage, String description , File prjImage, File partyAFile)  {
        try {
            int projectwidth = 500;
            int partyAwidth = 300;
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("${projectName}", projectName);
            params.put("${imageNumber}", imageNumber);
            params.put("${designer}", designer);
            params.put("${progress}", progress);
            params.put("${date}", date);
            params.put("${manage}", manage);
            params.put("${description}", description);

            if(prjImage!=null&&prjImage.exists()){
                Bitmap bitmapPro  =  BitmapFactory.decodeFile(prjImage.getAbsolutePath());
                int width = bitmapPro.getWidth();
                int height = bitmapPro.getHeight();
                int  paintProheight = (int) (((double)projectwidth/width)*height);

                Map<String, Object> header = new HashMap<String, Object>();
                header.put("width", projectwidth);
                header.put("height", paintProheight);
                header.put("type", "jpg");
//            header.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(Config.PATHS_TEMP+"/testphoto1.png"), true));
                header.put("content", WordUtils.File2byte(prjImage));
                params.put("${header}",header);
            }else{
                params.put("${header}","");
            }

            if(partyAFile!=null&&partyAFile.exists()){
                Bitmap bitmapPartyA  =  BitmapFactory.decodeFile(partyAFile.getAbsolutePath());
                int partyAMwidth = bitmapPartyA.getWidth();
                int partyAMheight = bitmapPartyA.getHeight();
                int  partyAAllheight = (int) (((double)partyAwidth/partyAMwidth)*partyAMheight);

                Map<String, Object> header2 = new HashMap<String, Object>();
                header2.put("width", partyAwidth);
                header2.put("height", partyAAllheight);
                header2.put("type", "jpg");
//            header2.put("content", WordUtils.inputStream2ByteArray(new FileInputStream(Config.PATHS_TEMP+"/testphoto2.png"), true));
                header2.put("content", WordUtils.File2byte(partyAFile));
                params.put("${header2}",header2);
            }else{
                params.put("${header2}","");
            }

            File file = new File(Config.PATHS_PRINTING);
            if(!file.exists()){
                file.mkdirs();
            }
            List<String[]> testList = new ArrayList<String[]>();
            InputStream inputStream = context.getAssets().open(DOC_TEMPLET_3);
            CustomXWPFDocument doc = new CustomXWPFDocument(inputStream);
            this.replaceInPara(doc, params);    //替换文本里面的变量
            this.replaceInTable(doc, params, testList); //替换表格里面的变量
            FileOutputStream fos = new FileOutputStream(Config.PATHS_PRINTING+"/"+projectName+".doc");
            doc.write(fos);
            this.close(fos);
            this.close(inputStream);
            return true;
        }catch (Exception e){
            L.log("MyJavascript","writeToWord :Exception::::::"+e.toString());
        }
        return false;
    }



    /**
     * 替换段落里面的变量
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInPara(CustomXWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params, doc);
        }
    }

    /**
     * 替换段落里面的变量
     *
     * @param para   要替换的段落
     * @param params 参数
     */
    private void replaceInPara(XWPFParagraph para, Map<String, Object> params, CustomXWPFDocument doc) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            int start = -1;
            int end = -1;
            String str = "";
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                if ('$' == runText.charAt(0) && '{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if ('}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }

            for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
            }

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if (str.indexOf(key) != -1) {
                    Object value = entry.getValue();

                    L.i("WordUtils","value:::::::::::::"+value.toString());

                    if(value==null|| value.toString()==null||"null".equals(value.toString())){
                        str = str.replace(key, "");
                        para.createRun().setText(str, 0);
                        break;
                    }if (value instanceof String) {
                        str = str.replace(key, value.toString());
                        para.createRun().setText(str, 0);
                        break;
                    } else if (value instanceof Map) {
                        str = str.replace(key, "");
                        Map pic = (Map) value;
                        int width = Integer.parseInt(pic.get("width").toString());
                        int height = Integer.parseInt(pic.get("height").toString());
                        int picType = getPictureType(pic.get("type").toString());
                        byte[] byteArray = (byte[]) pic.get("content");
                        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                        try {
                            //int ind = doc.addPicture(byteInputStream,picType);
                            //doc.createPicture(ind, width , height,para);
                            doc.addPictureData(byteInputStream, picType);
                            doc.createPicture(doc.getAllPictures().size() - 1, width, height, para);
                            para.createRun().setText(str, 0);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    private static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        for (int i = 0; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        int length = table.getRows().size();
        for (int i = 1; i < length - 1; i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                String s = tableList.get(i - 1)[j];
                cell.setText(s);
            }
        }
    }

    /**
     * 替换表格里面的变量
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInTable(CustomXWPFDocument doc, Map<String, Object> params, List<String[]> tableList) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            if (table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (this.matcher(table.getText()).find()) {
                    rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            paras = cell.getParagraphs();
                            for (XWPFParagraph para : paras) {
                                this.replaceInPara(para, params, doc);
                            }
                        }
                    }
                } else {
                    insertTable(table, tableList);  //插入数据
                }
            }
        }
    }


    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }


    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * 将输入流中的数据写入字节数组
     *
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
        return byteArray;
    }


    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    public static byte[] File2byte(File tradeFile){
        byte[] buffer = null;
        try
        {
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }



    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}