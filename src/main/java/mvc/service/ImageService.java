package mvc.service;

import mvc.entity.ImageEntity;
import mvc.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public List<ImageEntity> findAll() {
        return imageRepository.findAll();
    }

    public void save(ImageEntity image) {
        imageRepository.save(image);
    }

    public ImageEntity findById(long id) {
        return imageRepository.findById(id);
    }

    public void deleteById(long id) {
        imageRepository.deleteById(id);
    }

    public List<ImageEntity> findAllImageByCategory(String roomType) {
        List<ImageEntity> imageList = imageRepository.findAllImageByCategory(roomType);

        return imageList;
    }

    public ImageEntity findByCategoryId(long id) {
        return imageRepository.findByCategoryId(id);
    }
}
