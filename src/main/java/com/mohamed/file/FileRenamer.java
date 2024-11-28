package com.mohamed.file;

import com.mohamed.cmdColors.Colors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileRenamer {

    public static void renameFile(Path originalFilePath, Path renamedFilePath) {
        checkIfOriginalFileExists(originalFilePath);
        rename(originalFilePath, renamedFilePath);
    }

    private static void rename(Path originalFilePath, Path renamedFilePath) {
        try {
            Files.move(originalFilePath, renamedFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.printf("%s is renamed to %s %successfully%s",
                    originalFilePath.getFileName().toString(),
                    renamedFilePath.getFileName().toString(),
                    Colors.GREEN.getCode(),
                    Colors.RESET.getCode());

        } catch (IOException e) {
            System.out.println(Colors.RED.getCode()
                    + "Error renaming file: " + e.getMessage()
                    + Colors.RESET.getCode());
        }
    }

    private static void checkIfOriginalFileExists(Path originalFilePath) {
        if (!Files.exists(originalFilePath))
            throw new FileNotExists(String.format("%s:%s does not exist :(%s",
                    originalFilePath.getFileName().toString(),
                    Colors.RED.getCode(),
                    Colors.RESET.getCode())
            );
    }



}
