package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.NotificationVo;

@Mapper
public interface NotificationMapper {
	 // 특정 유저의 알림 목록 조회 (최신순)
    List<NotificationVo> selectByReceiverId(int userId);
    // 알람 요청
//    void insertNotification(NotificationVo noti);
    
//    <select id="selectNotificationsByUserId" resultType="NotificationVo">
    List<NotificationVo> selectNotificationsByUserId(Integer id);
    
//    <insert id="insertNotification" parameterType="NotificationVo">
    int insertNotification(NotificationVo notificationVo);
}