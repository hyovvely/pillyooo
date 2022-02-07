package com.multi.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	
	// 파일 업로드 폼으로 이동
	@RequestMapping("/fileUploadForm")
	public String fileUploadForm() {
		return "upload/fileUploadForm";
	}
	
	// 파일 업로드 처리 
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("uploadFile") MultipartFile file,
							Model model)throws IOException {
		
		String savedFileName = "";
		
		// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
		String uploadPath = "/Users/hyovvely/Desktop/springWorkspace/upload/";
		// 2. 원본 파일 이름 알아오기
		String originalFileName = file.getOriginalFilename();
		// 3. 파일 이름 중복되지 않도록 이름 변경 : 서버에 저장할 이름 . UUID 사용
		UUID uuid = UUID.randomUUID();
		savedFileName = uuid.toString() + "_" + originalFileName;

		// 4. 파일 생성
		File file1 = new File(uploadPath + savedFileName);
		// 5. 서버로 전송
		file.transferTo(file1); // throws IOException { 위에 이걸로 던진다? 
		// model로 저장 
		model.addAttribute("originalFileName", originalFileName);
		
		
		return "upload/fileUploadResult";
	}
	
	// 여러 개의 파일 업로드
	// 파일 업로드 처리
	@RequestMapping("/fileUploadMultiple")
	public String fileUploadMultiple(@RequestParam("uploadFileMulti")ArrayList<MultipartFile>files,
			Model model)throws IOException {
		
		String savedFileName = "";
		// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
		String uploadPath = "/Users/hyovvely/Desktop/springWorkspace/upload/";
		
		// 여러 개의 원본 파일 이름을 저장할 리스트 생성
		ArrayList<String> originalFilenameList = new ArrayList<String>();
		
		for(MultipartFile file:files) {
			// 2. 원본 파일 이름 알아오기
			String originalFileName = file.getOriginalFilename();
			
			// 파일 이름을 리스트에 추가
			originalFilenameList.add(originalFileName);
			
			// 3. 파일 이름 중복되지 않도록 이름 변경 : 서버에 저장할 이름 . UUID 사용
			UUID uuid = UUID.randomUUID();
			savedFileName = uuid.toString() + "_" + originalFileName;

			// 4. 파일 생성
			File file1 = new File(uploadPath + savedFileName);
			// 5. 서버로 전송
			file.transferTo(file1); // throws IOException { 위에 이걸로 던진다? 
			
			// model로 저장 
			model.addAttribute("originalFilenameList", originalFilenameList);
			
		}
		
		return "upload/fileUploadMultipleResult";
	}
	
	// 파일명 변경하지 않고 파일 업로드 처리 
		@RequestMapping("/fileOriginalNameUpload")
		public String fileOriginalNameUpload(@RequestParam("uploadFile") MultipartFile file,
								Model model)throws IOException {
			

			
			// 1. 파일 저장 경로 설정 : 실제 서비스되는 위치 (프로젝트 외부에 저장)
			String uploadPath = "/Users/hyovvely/Desktop/springWorkspace/upload/";
			// 2. 원본 파일 이름 알아오기
			String originalFileName = file.getOriginalFilename();
			// 3. 파일 생성
			File file1 = new File(uploadPath + originalFileName);
			// 4. 서버로 전송
			file.transferTo(file1); // throws IOException { 위에 이걸로 던진다? 
			// model로 저장 
			model.addAttribute("originalFileName", originalFileName);
			
			
			return "upload/fileUploadResult";
		}
}
