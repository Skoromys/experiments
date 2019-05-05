package ru.konovalov.service.util;

public interface ScannerService {
    void initScanner();

    boolean hasNextLine();

    String nextLine();
}
