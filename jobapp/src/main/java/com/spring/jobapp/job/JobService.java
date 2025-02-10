package com.spring.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job findById(Long id);
    void createJob(Job job);
    boolean deleteJob(Long id);
    boolean updateJob(Long id, Job job);
}
