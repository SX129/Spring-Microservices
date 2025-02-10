package com.microservice.jobms.job;

import com.microservice.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    JobDTO findById(Long id);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
