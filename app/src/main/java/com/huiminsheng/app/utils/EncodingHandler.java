package com.huiminsheng.app.utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

/**
 * 显示二维码使用到
 * @author Ryan Tang
 */
public final class EncodingHandler {
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xffffffff;
	private static final int TRAN = 0;

	public static Bitmap createQRCode(String str, int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
        hints.put(EncodeHintType.MARGIN, 2); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight,hints);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				}else{
					pixels[y * width + x] = WHITE;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	/**生成二维码时,不显示空白边界*/
	public static Bitmap createQRCodeNoPading(String str, int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		hints.put(EncodeHintType.MARGIN, 0); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight,hints);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				}else{
					pixels[y * width + x] = WHITE;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	public static Bitmap createQRCodeTran(String str, int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (matrix.get(x, y)) {
					pixels[y * width + x] = BLACK;
				}else{
					pixels[y * width + x] = TRAN;
				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	/*
	public static Bitmap Create2DCode(String str) throws WriterException {       
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败       
        BitMatrix matrix = new MultiFormatWriter().encode(str,BarcodeFormat.QR_CODE, 400, 400);       
        int width = matrix.getWidth();       
        int height = matrix.getHeight();       
        //二维矩阵转为一维像素数组,也就是一直横着排了       
        int[] pixels = new int[width * height];       
        for (int y = 0; y < height; y++) {       
            for (int x = 0; x < width; x++) {       
                if(matrix.get(x, y)){       
                    pixels[y * width + x] = 0xff000000;       
                }       
                       
            }       
        }       
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);       
        //通过像素数组生成bitmap,具体参考api       
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);       
        return bitmap;       
    }   
    */
}
