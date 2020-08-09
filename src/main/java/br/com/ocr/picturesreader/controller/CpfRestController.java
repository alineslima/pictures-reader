package br.com.ocr.picturesreader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ocr.picturesreader.service.OcrReaderService;

@RestController
public class CpfRestController {
	
	@Autowired
	private OcrReaderService ocrReaderService;
	
	@GetMapping("/cpf/find")
	public String getCpfByPath(@RequestParam String path){
		try {
			return ocrReaderService.readFileFromUrl(path);
		} catch (Exception e) {
			e.printStackTrace();
			return "Couldnt read 223";
			
		}
	}

}
