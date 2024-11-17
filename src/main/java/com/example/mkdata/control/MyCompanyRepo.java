package com.example.mkdata.control;

import com.example.mkdata.dto.MyCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyCompanyRepo extends JpaRepository<MyCompany,Integer> {


//    @Query(value = "SELECT * FROM Users u WHERE u.status = :status and u.name = :name", nativeQuery = true)
//    void creatNotExist(@Param("status") Integer status, @Param("name") String name);


    boolean existsByPhoneContainingIgnoreCase(String phone);
}
