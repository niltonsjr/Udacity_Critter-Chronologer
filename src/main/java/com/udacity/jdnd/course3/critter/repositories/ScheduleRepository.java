package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
