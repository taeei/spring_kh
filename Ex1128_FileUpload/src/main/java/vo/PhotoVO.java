package vo;

import org.springframework.web.multipart.MultipartFile;

public class PhotoVO {

	private String title, filename;
	
	//파일을 받기 위한 클래스
	private MultipartFile photo;
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	
	
}
