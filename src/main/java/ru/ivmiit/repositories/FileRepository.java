package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.File;

public interface FileRepository extends JpaRepository<File,Long> {
    File findFirstByUrl(String storageFileName);
}