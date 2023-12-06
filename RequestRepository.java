package com.example.project2;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<MaintReq, Long> {

    List<MaintReq> findByStatus(String status);
    MaintReq findByApartNum(int apartNum);
    MaintReq findByArea(String area);
    MaintReq findByDate(String date);
    MaintReq findById(long id);

    //A staff member must be able to browse all maintenance requests, with a variety of filters: by
    //apartment number, by area (like kitchen), by date range, and by status.
}
