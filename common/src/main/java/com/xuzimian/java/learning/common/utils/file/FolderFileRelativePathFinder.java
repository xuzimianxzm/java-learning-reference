package com.xuzimian.globaldemo.common.utils.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 文件夹文件相对地址查询器
 *
 * @program: global-demo
 * @description: 静态文件统配类
 * @author: xzm
 * @create: 2019-04-25 14:45
 **/

public class FolderFileRelativePathFinder {

    private String basePath;

    /**
     * 寻找指定文件夹下的，指定扩展名的文件的相对地址
     * @param folderPath
     * @param extensions 文件扩展名，无需符号"." ,如 new String[]{"xml", "propertites"}
     * @param isRemoveSuffixes  是否移除返回的相对地址的文件后缀名
     * @return
     * @throws IOException
     */
    public List<String> findPath(String folderPath, String[] extensions, boolean isRemoveSuffixes) throws IOException {
        return Optional.ofNullable(this.getClass().getResource(folderPath))
                .map(d -> findPath(new File(d.getPath()), extensions, isRemoveSuffixes))
                .orElseGet(() -> new ArrayList<String>());
    }

    private List<String> findPath(File file, String[] extensions, boolean isRemoveSuffixes) {
        basePath = file.getPath();
        List<String> list = new ArrayList<>();
        for (File temp : FileUtils.listFiles(file, extensions, true)) {
            if (isRemoveSuffixes) {
                list.add(relativeFliePath(temp.getPath(), extensions));
            } else {
                list.add(relativeFliePath(temp.getPath()));
            }
        }
        return list;

    }

    /**
     * 转换相对地址
     * @param path
     * @return
     */
    private String relativeFliePath(String path) {
        return path.substring(basePath.length()).replace("\\", "/");
    }

    /**
     * 转换成相对地址，并去掉文件后缀名
     * @param path
     * @param extensions
     * @return
     */
    private String relativeFliePath(String path, String[] extensions) {
        String relativePath = path.substring(basePath.length()).replace("\\", "/");
        for (String extension : extensions) {
            relativePath = relativePath.replace("." + extension, StringUtils.EMPTY);
        }
        return relativePath;
    }

}
