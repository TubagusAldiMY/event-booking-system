package com.sinaukoding.event_booking_system.service.app;

import com.sinaukoding.event_booking_system.model.enums.TipeUpload;
import com.sinaukoding.event_booking_system.model.response.BaseResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    BaseResponse<?> upload(MultipartFile files, TipeUpload tipeUpload);

    Resource loadFileAsResource(String pathFile);

}