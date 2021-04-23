package online.xuanwei.bbs.service;

import online.xuanwei.bbs.dto.NotificationDTO;
import online.xuanwei.bbs.mapper.NotificationMapper;
import online.xuanwei.bbs.mapper.NotificationMapperExt;
import online.xuanwei.bbs.mapper.UserMapper;
import online.xuanwei.bbs.model.Notification;
import online.xuanwei.bbs.model.NotificationExample;
import online.xuanwei.bbs.model.User;
import online.xuanwei.bbs.model.UserExample;
import online.xuanwei.bbs.util.NotificationEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private  UserMapper userMapper;

    @Autowired
    private NotificationMapperExt notificationMapperExt;

    public List<NotificationDTO> getNotifier(User user){
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().
                andReceiverEqualTo(user.getId().longValue())
                .andStatusEqualTo(0);
        List<Notification> notificationList = notificationMapper.selectByExample(notificationExample);

        if(notificationList.size() == 0) {
          return new ArrayList<>();
      }

        Set<Integer> notifierIds = notificationList.stream().map(notification -> notification.getNotifier().intValue()).collect(Collectors.toSet());
        Set<Integer> receiverIds = notificationList.stream().map(notification -> notification.getReceiver().intValue()).collect(Collectors.toSet());
        Set<Integer> userIds = Stream.concat(notifierIds.stream(),receiverIds.stream()).collect(Collectors.toSet());
        System.out.println("set大小"+userIds.size());

        List<Integer> integerList = new ArrayList<>();
        integerList.addAll(userIds);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(integerList);
        List<User> userList = userMapper.selectByExample(userExample);
        Map<Integer,User> userMap = userList.stream().collect(
                Collectors.toMap(user1 ->user1.getId(),user1 ->user1));
        System.out.println("user大小"+userMap.toString());
        List<NotificationDTO> notificationDTOS = notificationList.stream().map(notification ->
                {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    BeanUtils.copyProperties(notification,notificationDTO);
                    notificationDTO.setNotifierUser(userMap.get(notification.getNotifier().intValue()));
                    notificationDTO.setReceiverUser(userMap.get(notification.getReceiver().intValue()));
                    switch (notificationDTO.getType()){
                        case 1:
                            notificationDTO.setMessage(NotificationEnum.REPLY_QUESTION.getContent());
                            break;
                        case 2:
                            notificationDTO.setMessage(NotificationEnum.REPLY_COMMENTS.getContent());
                            break;
                    }
                    return notificationDTO;
                }).collect(Collectors.toList());
        return notificationDTOS;
    }

    public void readAll() {
        notificationMapperExt.updateStatus();

    }
}
