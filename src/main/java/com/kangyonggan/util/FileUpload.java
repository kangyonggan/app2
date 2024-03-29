package com.kangyonggan.util;

import com.kangyonggan.constants.AppConstants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author batizhao
 * @since 15/3/27
 */
public class FileUpload {

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception {
        String fileName = "";
        if (file.getSize() != 0) {
            fileName = extractFilePath(file);
            File desc = getAbsolutePath(fileName);
            file.transferTo(desc);
        }
        return fileName;
    }

    /**
     * 获取文件绝对路径
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static File getAbsolutePath(String filename) throws IOException {
        File desc = new File(AppConstants.APP_ROOT + filename);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 根据文件名和后缀提取上传后的文件路径，例如：2015032821561197_suffix.jpg
     *
     * @param fileName
     * @param suffix
     * @return
     */
    public static String extractFilePath(String fileName, String suffix) {
        String fileExt = FilenameUtils.getExtension(fileName);
        return extractFilePathByExtension(fileExt, suffix);
    }

    /**
     * 根据 MultipartFile 提取上传后的文件路径，例如：/upload/20150328/2015032821561197.jpg
     *
     * @param file
     * @return
     */
    public static String extractFilePath(MultipartFile file) {
        String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
        return extractFilePathByExtension(fileExt, "");
    }

    /**
     * 根据根据扩展名和后缀得到新的文件路径
     *
     * @param extension
     * @param suffix
     * @return
     */
    private static String extractFilePathByExtension(String extension, String suffix) {
        StringBuilder tempPath = new StringBuilder();

        tempPath.append(AppConstants.PATH_UPLOAD);

        tempPath.append(System.nanoTime());

        if (StringUtils.isNoneBlank(suffix)) {
            tempPath.append("_").append(suffix);
        }

        tempPath.append(".").append(extension);

        return tempPath.toString();
    }
}
