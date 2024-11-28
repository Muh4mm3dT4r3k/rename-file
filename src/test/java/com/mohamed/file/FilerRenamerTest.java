package com.mohamed.file;

import com.mohamed.cmdColors.Colors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class FilerRenamerTest {
    private static final String FILE_NOT_EXIST_MSG = "%s:%s does not exist :(%s";
    private static final String SUCCESS_RENAMED_MSG = "%s is renamed to %s %successfully%s";

    @TempDir
    private Path tempDir;
    private Path originalFilePathExists;
    private Path originalFilePathNotExists;
    private Path renamedFilePath;

    @BeforeEach
    void setUp() throws IOException {
        originalFilePathExists = Files.createFile(tempDir.resolve("original-file.txt"));
        renamedFilePath = tempDir.resolve("renamed-file.txt");
        originalFilePathNotExists = tempDir.resolve("no-file.txt");
    }

    @Test
    void shouldThrowExceptionIfOriginalFileDoesNotExist() {
        String fileNotExistMsg = String
                .format(FILE_NOT_EXIST_MSG,
                        originalFilePathNotExists.getFileName().toString(),
                        Colors.RED.getCode(), Colors.RESET.getCode());
        FileNotExists fileNotExists = assertThrows(FileNotExists.class, () -> {
            FileRenamer.renameFile(originalFilePathNotExists, renamedFilePath);
        });

        assertEquals(fileNotExistMsg, fileNotExists.getMessage());
    }

    @Test
    void shouldRenamedFilename(){
        String successRenamedMsg = String
                .format(SUCCESS_RENAMED_MSG,
                        originalFilePathExists.getFileName().toString(),
                        renamedFilePath.getFileName().toString(),
                        Colors.GREEN.getCode(), Colors.RESET.getCode());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        assertDoesNotThrow(() -> {
            FileRenamer.renameFile(originalFilePathExists, renamedFilePath);
        });

        System.setOut(originalOut);

        assertFalse(() -> Files.exists(originalFilePathExists));
        assertTrue(() -> Files.exists(renamedFilePath));
        assertEquals(successRenamedMsg, outContent.toString());
    }
}
