package online.xuanwei.bbs.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private Integer page;  //第机页
    private Integer pages; //共多少页
    private Integer size;  //一页大小
    private Integer nums;   //共多少条记录

    public void setPages(Integer size,Integer nums) {
        if(nums%size == 0){
            pages = nums/size;
        }else {
            pages = nums/size + 1;
        }
    }
}
