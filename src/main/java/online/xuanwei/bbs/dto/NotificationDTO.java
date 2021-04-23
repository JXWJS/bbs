package online.xuanwei.bbs.dto;

import lombok.Data;
import online.xuanwei.bbs.model.User;

@Data
public class NotificationDTO {
    private Long id;
    private Integer outerid;
    private Long gmtCreate;
    private Integer type;
    private Long notifier;
    private Long receiver;
    private User notifierUser;
    private  User receiverUser;
    private  String message;


}
