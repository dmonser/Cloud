package ru.netology.Cloud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.netology.Cloud.entities.File;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Override
    Optional<File> findById(Long aLong);

    Optional<File> findFileByOriginalName(String originalName);
}
