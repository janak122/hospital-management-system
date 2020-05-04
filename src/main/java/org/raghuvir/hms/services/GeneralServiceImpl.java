package org.raghuvir.hms.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
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

	final String IMG_UPLOAD_PATH = "F://spring/user_images/", DEFAULT_IMAGE = "default.png";

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

	public String uploadFile(String userId, MultipartFile file) {
		if (file != null) {
			File uploadDir = new File(this.IMG_UPLOAD_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					String fname = file.getOriginalFilename(), extension = "jpg";
					int i = fname.lastIndexOf('.');
					extension = (i > 0) ? fname.substring(i + 1) : extension;

					String fnm = userId + "." + extension;

					File uploadFile = new File(uploadDir, fnm);

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
		}
		return this.DEFAULT_IMAGE;
	}

	@Override
	public String getValidFilename(String fnm) {
		File file = new File(this.IMG_UPLOAD_PATH + "/" + fnm);
		return (file.exists()) ? fnm : this.DEFAULT_IMAGE;
	}

	@Override
	public List<Integer> getListForPageBar(int curr, int total, int MAX_PAGEBAR) {
		List<Integer> list = new LinkedList<>();
		int start = 1, end = total;
		if (total > MAX_PAGEBAR) {
			if (curr > MAX_PAGEBAR) {
				start = (curr > total - MAX_PAGEBAR) ? total - MAX_PAGEBAR + 1 : curr - (MAX_PAGEBAR / 2);
			}
			end = start + MAX_PAGEBAR - 1;
		}
		for (int i = start; i <= end; i++) {
			list.add(i);
		}
		return list;
	}
}
