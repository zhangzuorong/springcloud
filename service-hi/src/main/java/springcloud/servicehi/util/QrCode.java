package springcloud.servicehi.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import springcloud.servicehi.enumerate.ContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 开发公司：青岛上朝信息科技有限公司
 * 版权：青岛上朝信息科技有限公司
 * <p>
 * 类功能描述
 *
 * @author zhangzuorong
 * @created 2018/8/9.
 */
public class QrCode {

    // 二维码
    public final static int QR_CODE_WIDTH = 300;
    public final static int QR_CODE_HEIGHT = 300;
    public final static String QR_CODE_FORMAT = "png";

    public static String qrCodeStore(String content) {
        //定义二维码参数
        Map hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//设置容错等级
        hints.put(EncodeHintType.MARGIN, 2);//设置边距默认是5

        //dir name
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dirName = format.format(new Date());
        String filePathName = new StringBuilder()
                .append("QR-").append(new Date().getTime())
                .append("-").append(content)
                .append(".png").toString();
        ContentType contentType = ContentType.ContentTypeStr("png");
        String pathI = contentType.getPath();
        Path path = null;
        try {
            if(!Files.exists(Paths.get(pathI + dirName))){
                Files.createDirectories(Paths.get(pathI + dirName));
            }
            path = Files.createFile(Paths.get(pathI + dirName + "/" + filePathName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, hints);
            MatrixToImageWriter.writeToPath(bitMatrix, QR_CODE_FORMAT, path);//写到指定路径下
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dirName + "/" + filePathName;
    }
}
