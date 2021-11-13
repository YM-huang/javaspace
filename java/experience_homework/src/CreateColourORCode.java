import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CreateColourORCode {
	// ��ά��д����
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
 
    public static void encode(String content, int width, int height, String destImagePath) {
        try {
            //����ͼƬ�ļ�
            ImageIO.write(genBarcode(content, width, height), "jpg", new File(destImagePath));
            System.out.println("��ά�����ɳɹ���");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * �õ�BufferedImage
     *
     * @param content ��ά����ʾ���ı�
     * @param width   ��ά��Ŀ��
     * @param height  ��ά��ĸ߶�
     * @return
     * @throws WriterException
     * @throws IOException
     */
    private static BufferedImage genBarcode(String content, int width, int height) throws WriterException, IOException {
        //�����ά�����ݲ���
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //�����ַ��������ʽ
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //�����ݴ�ȼ�������������ʹ��M����
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // ���ɶ�ά�룬����˳��ֱ�Ϊ���������ݣ��������ͣ�����ͼƬ��ȣ�����ͼƬ�߶ȣ����ò���
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
 
        // ��ά����תΪһά��������
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int[] pixels = new int[width * height];
 
        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                // ��ά����ɫ��RGB��
//                int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight()
//                        * (y + 1));
//                int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()
//                        * (y + 1));
//                int num3 = (int) (162 - (162.0 - 107.0)
//                        / matrix.getHeight() * (y + 1));
                Color color = new Color(0, 95, 1);
                int colorInt = color.getRGB();
                // �˴������޸Ķ�ά�����ɫ�����Էֱ��ƶ���ά��ͱ�������ɫ��
                pixels[y * width + x] = matrix.get(x, y) ? colorInt : 16777215;// 0x000000:0xffffff
            }
        }
 
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);
 
        return image;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        // ����Ϊ����(��֧������),��,��,logoͼ��·��,����·��
        CreateColourORCode.encode("https://www.google.com/", 300, 300, "qrcode.jpg");
    }
    
}
