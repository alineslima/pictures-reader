package br.com.ocr.picturesreader.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OcrReaderService {
	
	public static void main(String[] args) throws Exception{
		OcrReaderService ocrReader = new OcrReaderService();
		System.out.println(ocrReader.readFileFromUrl("http://www.taiadaweb.com.br/wp-content/uploads/2017/01/cpf2.jpg"));
	}
	
	private File createImageFromUrl(String path, String imageTempName) throws Exception{
		File tempFile = new File(imageTempName);
		URL url = new URL(path);
		Image image = ImageIO.read(url);
		ImageIO.write((BufferedImage) image, "jpg", tempFile);
		return tempFile;
	}
	
	public String readFileFromUrl(String path) throws Exception{
		String imageTempName = "CPF-TEMP.jpg";
		File tempFile = createImageFromUrl(path, imageTempName);
        ITesseract instance = new Tesseract();
        String result = "";
        try {
        	instance.setDatapath(tempFile.getAbsolutePath().replace(imageTempName, "")+"\\tessdata");
        	instance.setLanguage("por");
            result = instance.doOCR(tempFile);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
		return result;
	}

}
