package online.xuanwei.bbs.mapper;

import java.util.List;
import online.xuanwei.bbs.model.Notification;
import online.xuanwei.bbs.model.NotificationExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
@Mapper
public interface NotificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    long countByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int deleteByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int insert(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int insertSelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    List<Notification> selectByExampleWithRowbounds(NotificationExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    List<Notification> selectByExample(NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    Notification selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int updateByPrimaryKeySelective(Notification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbg.generated Thu Apr 22 14:57:21 CST 2021
     */
    int updateByPrimaryKey(Notification record);
}