package net.realme.mall.oms.core.beantool;


import net.realme.mall.oms.core.model.FileUpload;
import net.realme.mall.oms.domain.core.FileUploadDto;
import net.realme.mall.oms.util.PathManager;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(FileUploadConverterDecorator.class)
public interface FileUploadConverter {

     FileUpload fromFileUploadDto(FileUploadDto fileUploadDto);

     FileUploadDto toFileUploadDto(FileUpload fileUpload);

     List<FileUploadDto> toFileUploadDtoList(List<FileUpload> fileList);
}

abstract class FileUploadConverterDecorator implements FileUploadConverter {

     @Autowired
     @Qualifier("delegate")
     private FileUploadConverter delegate;

     @Autowired
     private PathManager pathManager;

     @Override
     public FileUploadDto toFileUploadDto(FileUpload fileUpload) {
          FileUploadDto fileUploadDto = delegate.toFileUploadDto(fileUpload);
          if (StringUtils.isNotBlank(fileUploadDto.getPath())) {
               fileUploadDto.setUrl(pathManager.getS3Url(fileUploadDto.getPath()));
          }
          return fileUploadDto;
     }

     @Override
     public List<FileUploadDto> toFileUploadDtoList(List<FileUpload> fileList) {
          if ( fileList == null ) {
               return null;
          }

          List<FileUploadDto> list1 = new ArrayList<>( fileList.size() );
          for ( FileUpload fileUpload : fileList ) {
               list1.add( this.toFileUploadDto( fileUpload ) );
          }

          return list1;
     }
}