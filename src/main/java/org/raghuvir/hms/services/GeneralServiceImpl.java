package org.raghuvir.hms.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.raghuvir.hms.daos.GeneralDAO;
import org.raghuvir.hms.daos.GeneralDAOImpl;
import org.raghuvir.hms.utils.EntitiesConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("generalservices")
public class GeneralServiceImpl implements GeneralService {

	GeneralDAO instance = GeneralDAOImpl.getInstance();

	@Override
	public void insertUpdate(Object entity) {
		instance.insertUpdate(entity);
	}

	@Override
	public void delete(Object entity) {
		instance.delete(entity);
	}

	@Override
	public Object getEntity(Class clazz, Serializable key) {
		return instance.getEntity(clazz, key);
	}

	@Override
	public List getList(Class clazz, int start, int size, Criterion... criterions) {
		return instance.getList(clazz, start, size, criterions);
	}

	public String uploadFile(String dirpath, String userId, MultipartFile file) {
		File uploadDir = new File("/spring/hospitalmanagementsystem/src/main/webapp/resources/images");
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
//		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String fname = file.getOriginalFilename(), extension = "jpg";
				int i = fname.lastIndexOf('.');
				extension=(i>0)?fname.substring(i + 1):extension;

				String fnm = userId + "." + extension;
//				System.out.println(dirpath);
				File uploadFile = new File(uploadDir, fnm);
				System.out.println(uploadFile.getAbsolutePath());
				uploadFile.createNewFile();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
				stream.write(bytes);
				stream.flush();
				stream.close();
				return fnm;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return EntitiesConstants.DEFAULT_IMAGE;
	}
}
