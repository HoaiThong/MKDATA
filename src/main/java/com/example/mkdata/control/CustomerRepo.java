package com.example.mkdata.control;

import com.example.mkdata.dto.MyCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<MyCompany,Integer> {

}