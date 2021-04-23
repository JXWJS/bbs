package online.xuanwei.bbs.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapperExt {
    void updateStatus();
}
