package online.xuanwei.bbs.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PageDTO {
    private Integer offset;
    private  Integer size;
    private String search;
    private Integer categoryId;

}
