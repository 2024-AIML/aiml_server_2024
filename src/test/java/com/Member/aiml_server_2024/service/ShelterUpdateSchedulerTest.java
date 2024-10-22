//package com.Member.aiml_server_2024.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.scheduling.TaskScheduler;
//import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
//import org.springframework.scheduling.support.PeriodicTrigger;
//
//import java.util.concurrent.ScheduledFuture;
//
//import static org.mockito.Mockito.*;
//
//public class ShelterUpdateSchedulerTest {
//
//    @Mock
//    private CountingService countingService;
//
//    @InjectMocks
//    private ShelterUpdateScheduler shelterUpdateScheduler;
//
//    private TaskScheduler taskScheduler;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        taskScheduler = new ConcurrentTaskScheduler();
//    }
//
//    @Test
//    public void testUpdateShelters() throws Exception {
//        // CountingService의 updateOccupiedUsersInShelters 메서드가 호출될 것으로 예상
//        doNothing().when(countingService).updateOccupiedUsersInShelters();
//
//        shelterUpdateScheduler.updateShelters();
//        // CountingService 메서드가 1번 호출되었는지 확인
//        verify(countingService, times(1)).updateOccupiedUsersInShelters();
//    }
//
//    @Test
//    public void testSchedulerIsTriggered() throws Exception {
//        // CountingService의 메서드가 스케줄에 따라 호출될 것으로 예상
//        doNothing().when(countingService).updateOccupiedUsersInShelters();
//
//        // 60초마다 실행되도록 스케줄링된 작업을 수동으로 트리거
//        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(shelterUpdateScheduler::updateShelters, new PeriodicTrigger(60000));
//
//        // 트리거를 기다림 (실제 상황에서는 시간이 지나야 함)
//        Thread.sleep(100);
//        // CountingService 메서드가 적어도 한 번 호출되었는지 확인
//        verify(countingService, atLeastOnce()).updateOccupiedUsersInShelters();
//
//        // 작업이 무한정 실행되지 않도록 스케줄링된 작업 취소
//        scheduledFuture.cancel(true);
//    }
//}
