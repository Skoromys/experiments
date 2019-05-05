package ru.konovalov.service.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ScannerServiceImpl implements ScannerService {

    private Scanner scanner;

    @Override
    public void initScanner() {
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
