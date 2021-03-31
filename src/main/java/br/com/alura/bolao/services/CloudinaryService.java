package br.com.alura.bolao.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {
	Cloudinary cloudinary;

	private Map<String, String> configCloud = new HashMap<>();

	Logger logger = LoggerFactory.getLogger(CloudinaryService.class);

	public CloudinaryService() {

		configCloud.put("cloud_name", "phbc");
		configCloud.put("api_key", "877353142363398");
		configCloud.put("api_secret", "BGdoNPtwHPTUXKdGqJs9Jl0KFf0");
		cloudinary = new Cloudinary(configCloud);

	}

	public Map upload(MultipartFile multipartfile, String folder) throws IOException {

		File file = this.convert(multipartfile);

		// Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
		Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", folder, "use_filename", "true"));
		file.delete();

		return result;
	}

	public Map delete(String public_id) throws IOException {

		Map result = cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());

		return result;
	}

	private File convert(MultipartFile multipartefile) throws IOException {

		File file = new File(multipartefile.getOriginalFilename());
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartefile.getBytes());
		fo.close();

		return file;
	}

}
