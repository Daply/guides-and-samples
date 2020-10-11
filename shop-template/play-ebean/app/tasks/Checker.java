package tasks;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import repositories.ProductRepository;
import tasks.actors.CheckerActorProtocol;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class Checker {

    public final ProductRepository productRepository;
    public final Config config;

    @Inject
    public Checker(ProductRepository productRepository, Config config) {
        this.productRepository = productRepository;
        this.config = config;
    }

    public void job(CheckerActorProtocol.CheckerMessage message) {
        switch (message.messageNumber) {
            case 1:
                checkUnusedImages();
                break;
        }
    }

    public void checkUnusedImages() {
        this.productRepository.getAllProductsImagesPaths().thenApplyAsync(images ->
                deleteLeftFiles(images.collect(Collectors.toSet()))
        );
    }

    private boolean deleteLeftFiles(Set<String> existingPaths) {
        System.out.println("deleting left files");
        String folderPath = config.getString("imagesFilesPath");
        File file = new File(folderPath);
        List<String> leftFiles = new ArrayList<>();
        if (file.isDirectory()) {
            leftFiles = Arrays.asList(file.list())
                    .stream()
                    .filter(imageInFolder -> !deleteLeftFile(existingPaths, folderPath + imageInFolder))
                    .collect(Collectors.toList());
        }
        if (leftFiles.isEmpty()) {
            log.info("files ok");
            return true;
        }
        return false;
    }

    private boolean deleteLeftFile(Set<String> existingPaths, String path) {
        if (!existingPaths.contains(path)) {
            File file = new File(path);
            return file.delete();
        }
        return false;
    }

}
