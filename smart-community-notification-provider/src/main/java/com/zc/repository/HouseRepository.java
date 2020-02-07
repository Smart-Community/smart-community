package com.zc.repository;

import com.zc.pojo.House;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author 小帅气
 * @create 2020-02-06-23:14
 */
@Repository
public interface HouseRepository extends JpaRepository<House,Long>, JpaSpecificationExecutor<House> {
    @Query("select t from House t where t.houseTungId=:tungId and t.houseUnitId=:unitId and t.houseNumber=:number")
    House findByTungIdAndUnitIdAndNumber(@Param("tungId") int tungId, @Param("unitId")int unitId,@Param("number") int number);
}
