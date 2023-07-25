package com.nio.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author danghf
 * @version 1.0
 * @date 2023/6/18/0018 18:25
 * Paths 和 Files
 */
public class FileAndPath {
    private static final Logger logger = LoggerFactory.getLogger(FileAndPath.class);

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\java\\netty\\src\\main\\java");
        if (!Files.exists(path)) {
            logger.info("文件不存在");
        }
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            // 进入目录之前
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                logger.info("访问目录{}", dir);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                logger.info("正在访问{}", file);
                return super.visitFile(file, attrs);
            }

            @Override
            // 访问失败
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                logger.info("访问文件{}失败", file);
                return super.visitFileFailed(file, exc);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                logger.info("访问目录{}之后", dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }
}
