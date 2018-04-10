package ru.ivmiit.service;

import ru.ivmiit.repositories.BaseRepository;

public interface StorageService {
    BaseRepository getProductRepository();

    BaseRepository getUserRepository();
}
