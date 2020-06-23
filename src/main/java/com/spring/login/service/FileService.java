package com.spring.login.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FileService {

    /**
     *  文件上传
     * @param inputStream
     */
    public void upload(FileInputStream inputStream ){
        FileChannel fileChannel = null;
        // 获取上次最后一行和本次第一行数据字节
        byte [] tempByte = new byte[0];
        // 每次读取到的数据
        byte [] bytes = new byte[0];
        // 每行读取到的字节
        byte [] lineByte = new byte[0];
        ByteBuffer buffer = ByteBuffer.allocate(10);
        List<String> stringList = new ArrayList<>(1000);
        int len = 0;
        log.info("----------- 文件内容输出 -----------");
        try {
            fileChannel = inputStream.getChannel();
            while ( (len = fileChannel.read(buffer)) != -1) {
                // 读取字节的长度
                bytes = new byte[buffer.position()];
                // 设置下标到其实位置
                buffer.rewind();
                // 将buffer读取到的字节写入bytes
                buffer.get(bytes);
                List<String> list = getStringByBytes(bytes,lineByte,tempByte);
                stringList.addAll(list);
                buffer.clear();
            }
            // 处理最后一次不足一行的数据
            if ( null != tempByte && tempByte.length > 0 ) {
                System.out.println(new String(tempByte, 0, tempByte.length, StandardCharsets.UTF_8));
                stringList.add(new String(tempByte, 0, tempByte.length, StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            log.error("file upload error", e);
        } finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  获取字节中的list
     * @param bytes
     * @return
     */
    private List<String> getStringByBytes(byte [] bytes,byte [] lineByte, byte[] tempByte){
        boolean hashCrlf = false;
        List<String> arrayList = new ArrayList<>(1000);
        int startIndex = 0;
        String lineValue = null;
        int bytesNum = bytes.length;
        for ( int i = 0; i < bytesNum; i ++ ) {
            // 换行符
            if ( bytes[i] == 10 ) {
                hashCrlf = true;
                int tempNum = tempByte.length;
                int lineNum = i - startIndex;
                // 单行的字节数组
                lineByte = new byte[tempNum + lineNum];
                // 存储上次最后一行数据
                System.arraycopy(tempByte,0,lineByte,0,tempNum);
                tempByte = new byte [0];
                // 追加本次每行数据
                System.arraycopy(bytes,startIndex,lineByte,tempNum,lineNum);
                lineValue = new String(lineByte,0,lineByte.length, StandardCharsets.UTF_8);
                System.out.println(lineValue);
                if ( lineValue != null && lineValue.length() > 0 ) {
                    arrayList.add(lineValue);
                }
                // 如果当前换行符后面是回车符号,则下一行开始位置在回车符号后面
                if ( i + 1 < bytesNum && bytes[i + 1] == 13 ) {
                    startIndex = i + 2;
                } else {
                    // 下一行的开始位置为当前位置下标 + 换行符长度
                    startIndex = i + 1;
                }
            }
        }
        // 从最后一个换行符开始截取甚于数据的字节(不满足一行)
        if ( hashCrlf ) {
            tempByte = new byte[bytes.length - startIndex];
            // 存储剩余不足一行的数据字节,用于下一批拼接
            System.arraycopy(bytes,startIndex,tempByte,0,tempByte.length);
        } else {
            // 单次数读取不足一行时,将上次不足一行剩余的字节与本次读取到的数据拼接
            byte [] lastByte = new byte[tempByte.length + bytes.length ];
            System.arraycopy(tempByte, 0,lastByte,0,tempByte.length);
            System.arraycopy(bytes,0,lastByte,tempByte.length,bytes.length);
            tempByte = lastByte;
        }
        return arrayList;
    }

}
